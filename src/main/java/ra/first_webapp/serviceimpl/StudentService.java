package ra.first_webapp.serviceimpl;



import ra.first_webapp.entity.Student;
import ra.first_webapp.service.IStudentService;
import ra.first_webapp.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService implements IStudentService {
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        conn = ConnectDB.getConnection();
        try {

            PreparedStatement pre = conn.prepareStatement("select  * from Student");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPhone(rs.getString("phone"));
                s.setAddress(rs.getString("address"));
                s.setAge(rs.getInt("age"));
                s.setSex(rs.getBoolean("sex"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<Student> searchingAndSorting(String name, String sortFor, String sortBy) {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        conn = ConnectDB.getConnection();
        try {

            PreparedStatement pre = conn.prepareStatement("select  * from Student where name like ? order by " +sortFor +" "+ sortBy);
           pre.setString(1,"%"+name+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPhone(rs.getString("phone"));
                s.setAddress(rs.getString("address"));
                s.setAge(rs.getInt("age"));
                s.setSex(rs.getBoolean("sex"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public Student findById(Integer id) {
        Student s =null;
        Connection conn = null;
        conn = ConnectDB.getConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(
                    "select * from Student where id = ? ");
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
            s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setPhone(rs.getString("phone"));
            s.setAddress(rs.getString("address"));
            s.setAge(rs.getInt("age"));
            s.setSex(rs.getBoolean("sex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return s;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        conn = ConnectDB.getConnection();
        PreparedStatement pre = null;
        try {
            if(findById(student.getId())==null){
                // thêm  mới
                pre = conn.prepareStatement(
                        "Insert into Student(name,phone,address,sex,age)" +
                        " values(?,?,?,?,?) ");
                pre.setString(1,student.getName());
                pre.setString(2,student.getPhone());
                pre.setString(3,student.getAddress());
                pre.setBoolean(4,student.isSex());
                pre.setInt(5,student.getAge());

            }else {
//                cập nhật
                pre = conn.prepareStatement(
                        "update student " +
                                "set name = ?," +
                                "phone = ?," +
                                "address= ?," +
                                "sex = ?,age=? where id = ?");
                pre.setString(1,student.getName());
                pre.setString(2,student.getPhone());
                pre.setString(3,student.getAddress());
                pre.setBoolean(4,student.isSex());
                pre.setInt(5,student.getAge());
                pre.setInt(6,student.getId());
            }
            pre.executeUpdate();
        } catch (SQLException e) {
            return false;
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return true;
    }

    @Override
    public List<Student> searchingAndSortingInJava(String name, String sortFor, String sort) {
        return findAll().stream().filter(s->s.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted((s1,s2)->{
                    if (sortFor.equalsIgnoreCase("name")){
                        if (sort.equalsIgnoreCase("asc")){
                            return s1.getName().compareTo(s2.getName());
                        }else {
                            return s2.getName().compareTo(s1.getName());
                        }
                    }else {
                        if (sort.equalsIgnoreCase("asc")){
                            return s1.getAge()-s2.getAge();
                        }else {
                            return s2.getAge()-s1.getAge();
                        }
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        Connection conn = null;
        conn = ConnectDB.getConnection();
        try {
            PreparedStatement pre = conn.prepareStatement(
                    "delete from Student where id = ?");
            pre.setInt(1,id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}

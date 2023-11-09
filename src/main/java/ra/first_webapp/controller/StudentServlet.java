package ra.first_webapp.controller;

import ra.first_webapp.entity.Student;
import ra.first_webapp.service.IStudentService;
import ra.first_webapp.serviceimpl.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {

    // sắp xếp theo tên, tuổi tăng dần, giảm dần
    // tìm kiếm tương đối
    private IStudentService studentService ;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null){
            switch (action){
                case "GETALL":
                    showListStudent(studentService.findAll(),request,response);
                    break;
                case "ADD":
                    request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request,response);
//                    response.sendRedirect("/WEB-KAKE/add.jsp");
                    break;
                case "DELETE":
                    int idDel = Integer.parseInt(request.getParameter("id"));
                    studentService.deleteById(idDel);
                    showListStudent(studentService.findAll(),request,response);
                    break;
                case "EDIT":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Student s = studentService.findById(idEdit);
                    request.setAttribute("student",s);
                    request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request,response);
                    break;
                case "SEARCH":
                    String searchName = request.getParameter("searchName");
                    String sortFor = request.getParameter("sortFor");
                    String sortBy = request.getParameter("sortBy");

                    request.setAttribute("searchName",searchName);
                    request.setAttribute("sortFor",sortFor);
                    request.setAttribute("sortBy",sortBy);
                    showListStudent(studentService.searchingAndSortingInJava(searchName,sortFor,sortBy),request,response);
                    break;
            }
        }
    }
    protected void showListStudent(List<Student> list,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("students",list);
        request.getRequestDispatcher("/WEB-INF/listStudent.jsp").forward(request,response);

//        response.setContentType("text/html");

        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Danh sách sinh viên</h1>\n" +
//                "<a href=\"/StudentServlet?action=ADD\">Thêm mới sinh viên</a>\n" +
//                "<table class=\"table\">\n" +
//                "    <thead>\n" +
//                "    <tr>\n" +
//                "        <th scope=\"col\">ID</th>\n" +
//                "        <th scope=\"col\">Name</th>\n" +
//                "        <th scope=\"col\">Phone</th>\n" +
//                "        <th scope=\"col\">Address</th>\n" +
//                "        <th scope=\"col\">Sex</th>\n" +
//                "        <th scope=\"col\" colspan=\"2\">Action</th>\n" +
//                "    </tr>\n" +
//                "    </thead>\n" +
//                "    <tbody>\n");
//        for (Student c: list
//             ) {
//            out.println( "        <tr>\n" +
//                    "            <th>"+c.getId()+"</th>\n" +
//                    "            <td>"+c.getName()+"</td>\n" +
//                    "            <td>"+c.getPhone()+"</td>\n" +
//                    "            <td>"+c.getAddress()+"</td>\n" +
//                    "            <td>"+(c.isSex()?"Nam":"Nữ")+"</td>\n" +
//                    "            <td><a href=\"/StudentServlet?action=EDIT&id=${c.id}\">Edit</a></td>\n" +
//                    "            <td><a onclick=\"return confirm('Bạn có chắc chắn muốn xóa không')\"\n" +
//                    "                   href=\"/StudentServlet?action=DELETE&id=${c.id}\">Delete</a></td>\n" +
//                    "        </tr>\n");
//        };
//                out.println("\n" +
//                "    </tbody>\n" +
//                "</table>");
//        out.println("</body></html>");




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action!=null){
            switch (action){
                case "ADD":
                    String name =  request.getParameter("name");
                    String phone =  request.getParameter("phone");
                    String address =  request.getParameter("address");
                    int age = Integer.parseInt(request.getParameter("age"));
                    boolean sex =  Boolean.parseBoolean(request.getParameter("sex"));
                    Student s = new Student(0,name,phone,address,sex,age);

                    studentService.save(s);
                    showListStudent(studentService.findAll(),request,response);
                    break;
                case "UPDATE":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    String nameUp =  request.getParameter("name");
                    String phoneUp =  request.getParameter("phone");
                    String addressUp =  request.getParameter("address");
                    int ageUp = Integer.parseInt(request.getParameter("age"));
                    boolean sexUp =  Boolean.parseBoolean(request.getParameter("sex"));
                    Student studentUpdate = new Student(idEdit,nameUp,phoneUp,addressUp,sexUp,ageUp);
                    studentService.save(studentUpdate);
                    showListStudent(studentService.findAll(),request,response);
            }
        }
    }
}
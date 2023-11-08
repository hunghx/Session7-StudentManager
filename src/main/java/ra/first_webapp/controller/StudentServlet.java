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
                    getALLStudent(request,response);
                    break;
                case "ADD":
                    request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request,response);
//                    response.sendRedirect("/WEB-KAKE/add.jsp");
                    break;
                case "DELETE":
                    int idDel = Integer.parseInt(request.getParameter("id"));
                    studentService.deleteById(idDel);
                    getALLStudent(request,response);
                    break;
                case "EDIT":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Student s = studentService.findById(idEdit);
                    request.setAttribute("student",s);
                    request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request,response);
                    break;
            }
        }
    }
    protected void getALLStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.findAll();
        request.setAttribute("students",list);
        request.getRequestDispatcher("/WEB-INF/listStudent.jsp").forward(request,response);
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
                    boolean sex =  Boolean.parseBoolean(request.getParameter("sex"));
                    Student s = new Student(0,name,phone,address,sex);

                    studentService.save(s);
                    getALLStudent(request,response);
                    break;
                case "UPDATE":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    String nameUp =  request.getParameter("name");
                    String phoneUp =  request.getParameter("phone");
                    String addressUp =  request.getParameter("address");
                    boolean sexUp =  Boolean.parseBoolean(request.getParameter("sex"));
                    Student studentUpdate = new Student(idEdit,nameUp,phoneUp,addressUp,sexUp);
                    studentService.save(studentUpdate);
                    getALLStudent(request,response);
            }
        }
    }
}
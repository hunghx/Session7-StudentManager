package ra.first_webapp.service;

import ra.first_webapp.entity.Student;

import java.util.List;

public interface IStudentService extends IGenericService<Student,Integer> {
    List<Student> searchingAndSorting(String name, String sortFor,String sortBy);

    List<Student> searchingAndSortingInJava(String name, String sortFor,String sort);
}

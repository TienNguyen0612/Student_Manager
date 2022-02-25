package codegym.service;

import codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface IStudentService extends IService<Student> {
    ArrayList<Student> getAllStudentByName(String name);

    Page<Student> findAllInPage(Pageable pageable);
}

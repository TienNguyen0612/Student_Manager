package codegym.service;

import codegym.model.Student;

import java.util.ArrayList;

public interface IStudentService extends IService<Student> {
    ArrayList<Student> getAllStudentByName(String name);
}

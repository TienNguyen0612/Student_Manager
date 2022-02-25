package codegym.service.impl;

import codegym.model.Student;
import codegym.repository.IStudentRepository;
import codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public ArrayList<Student> findAll() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(int id) {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public ArrayList<Student> getAllStudentByName(String name) {
        return (ArrayList<Student>) studentRepository.findAllByNameContaining(name);
    }
}

package codegym.controller;

import codegym.model.Province;
import codegym.model.Student;
import codegym.service.IProvinceService;
import codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;
//    private final String view = "/images/";

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IProvinceService provinceService;

    @GetMapping
    public ModelAndView showStudents(@PageableDefault(value = 2) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Student> students = studentService.findAllInPage(pageable);
        if (students.isEmpty()) {
            modelAndView.addObject("message", "No Students !!!");
        }
        modelAndView.addObject("file", view);
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createStudent() {
        ModelAndView modelAndView = new ModelAndView("create");
        ArrayList<Province> provinces = provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        ModelAndView modelAndView = new ModelAndView("create");
        MultipartFile multipartFile = student.getImageFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(student.getImageFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        student.setImageUrl(fileName);
        Student studentCreate = studentService.save(student);
        if (studentCreate != null) {
            modelAndView.addObject("message", "Create Student Successfully !!!");
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/student");
        studentService.delete(id);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView showDetail(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Student student = studentService.findById(id);
        modelAndView.addObject("student", student);
        modelAndView.addObject("file", view);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Student student = studentService.findById(id);
        ArrayList<Province> provinces = provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        modelAndView.addObject("student", student);
        modelAndView.addObject("file", view);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView updateStudent(@PathVariable int id, @ModelAttribute Student student) {
        ModelAndView modelAndView = new ModelAndView("edit");
        student.setId(id);
        if (student.getImageFile().getSize() != 0) {
            MultipartFile multipartFile = student.getImageFile();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(student.getImageFile().getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            student.setImageUrl(fileName);
        } else {
            student.setImageUrl(studentService.findById(id).getImageUrl());
        }
        Student studentEdit = studentService.save(student);
        if (studentEdit != null) {
            modelAndView.addObject("file", view);
            modelAndView.addObject("message", "Update Student Successfully !!!");
        }
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") String name) {
        ModelAndView modelAndView = new ModelAndView("list");
        ArrayList<Student> students = studentService.getAllStudentByName(name);
        if (students.isEmpty()) {
            modelAndView.addObject("message", "Don't search students !!!");
        }
        modelAndView.addObject("file", view);
        modelAndView.addObject("students", students);
        modelAndView.addObject("search", name);
        return modelAndView;
    }
}

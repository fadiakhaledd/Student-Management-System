package com.example.studentmanagementsystem.Controllers;

import com.example.studentmanagementsystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/student")
public class StudentController {

    private final StudentService studentService;

    // @Autowired is used to inject the bean dependencies automatically.
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
}

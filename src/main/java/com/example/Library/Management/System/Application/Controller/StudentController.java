package com.example.Library.Management.System.Application.Controller;

import com.example.Library.Management.System.Application.Entities.Student;
import com.example.Library.Management.System.Application.Services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        return studentServices.addStudent(student);
    }
}

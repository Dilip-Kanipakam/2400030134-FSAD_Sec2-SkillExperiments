package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {

        if(id != 1) {
            throw new RuntimeException("Student not found");
        }

        return new Student(1,"Dilip","Computer Science");
    }
}
package com.klu.demo.controller;

import com.klu.demo.entity.Student;
import com.klu.demo.exception.InvalidInputException;
import com.klu.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        try {
            Long studentId = Long.parseLong(id);
            return service.getStudentById(studentId);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid ID format. Please enter a number.");
        }
    }
}
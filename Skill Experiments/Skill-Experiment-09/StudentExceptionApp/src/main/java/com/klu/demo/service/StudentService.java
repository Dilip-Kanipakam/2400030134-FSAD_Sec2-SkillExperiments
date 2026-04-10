package com.klu.demo.service;

import com.klu.demo.entity.Student;
import com.klu.demo.exception.StudentNotFoundException;
import com.klu.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    public Student save(Student student) {
        return repo.save(student);
    }
}
package com.klu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id = 21;
    private String name = "Sai Krishna";
    private String gender = "Male";

    @Autowired
    private Course course;

    public void display() {
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Gender       : " + gender);
        System.out.println("Course       : " + course);
    }
}
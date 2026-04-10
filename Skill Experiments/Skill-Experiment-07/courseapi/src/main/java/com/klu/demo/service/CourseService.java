package com.klu.demo.service;

import com.klu.demo.model.Course;
import java.util.List;

public interface CourseService {

    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    List<Course> searchByTitle(String title);
}
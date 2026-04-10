package com.klu.demo.service.impl;

import com.klu.demo.model.Course;
import com.klu.demo.repository.CourseRepository;
import com.klu.demo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Course saveCourse(Course course) {
        return repo.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(course.getTitle());
            existing.setDuration(course.getDuration());
            existing.setFee(course.getFee());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Course> searchByTitle(String title) {
        return repo.findByTitleContaining(title);
    }
}
package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    0    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        Course saved = service.addCourse(course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id) {
        Course course = service.getCourseById(id);
        if (course == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(course);
    }

        @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,
                                          @RequestBody Course course) {
        Course updated = service.updateCourse(id, course);
        if (updated == null)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        boolean deleted = service.deleteCourse(id);
        if (!deleted)
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok("Course deleted successfully");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<?> search(@PathVariable String title) {
        List<Course> list = service.searchByTitle(title);
        if (list.isEmpty())
            return new ResponseEntity<>("No courses found", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(list);
    }
}
package com.klu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.demo.model.Course;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitleContaining(String title);
}
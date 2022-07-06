package com.bmj.learningapp.repository;

import com.bmj.learningapp.model.Course;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends SearchRepository<Course, Long> {
}
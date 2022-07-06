package com.bmj.learningapp.service;

import com.bmj.learningapp.model.Course;
import com.bmj.learningapp.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CourseService {


	private CourseRepository courseRepository;

    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("name","hours");

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> searchCourses(String text, List<String> fields, int limit) {

        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream(). anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if(containsInvalidField) {
            throw new IllegalArgumentException();
        }

        return courseRepository.searchBy(
                text, limit, fieldsToSearchBy.toArray(new String[0]));
    }
    
    public List<Course> searchCoursesWithTotalHours(String text, List<String> fields, int limit) {
    	List<Course> searchedCourses = searchCourses(text, fields, limit);
    	return searchedCourses;
    }
    
    public List<Course> getAllCourses() {
    	//return courseRepository.findAll();
    	List<Course> courses = new ArrayList<>();
    	courseRepository.findAll().forEach(courses::add);
    	return courses;
    }
    
    public Course add(Course course) {
    	return courseRepository.save(course);
    }
}
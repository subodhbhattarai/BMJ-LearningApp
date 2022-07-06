package com.bmj.learningapp.controller;

import com.bmj.learningapp.model.Course;
import com.bmj.learningapp.model.SearchRequestDTO;
import com.bmj.learningapp.repository.CourseRepository;
import com.bmj.learningapp.service.CourseService;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
    CourseRepository courseRepository;
	
	private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    //private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(SearchRequestDTO searchRequestDTO) {

        log.info("Request for course search received with data : " + searchRequestDTO);

        //return 
        List<Course> searchedCourses = new ArrayList<Course>();
        searchedCourses = courseService.searchCourses(searchRequestDTO.getText(), searchRequestDTO.getFields(), searchRequestDTO.getLimit());
        if (searchedCourses.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(searchedCourses, HttpStatus.OK);
    }
    
    @GetMapping("/search/total")
    public ResponseEntity<List<Course>> searchCoursesWithTotalHours(SearchRequestDTO searchRequestDTO) {

        log.info("Request for course search received with data : " + searchRequestDTO);

        //return 
        List<Course> searchedCourses = new ArrayList<Course>();
        searchedCourses = courseService.searchCoursesWithTotalHours(searchRequestDTO.getText(), searchRequestDTO.getFields(), searchRequestDTO.getLimit());
        // add logic for total of courses
    	double total = 0; 
    	for (Course course : searchedCourses) {
    		total = total + Double.parseDouble(course.getHours());
    	}
    	String totalS = String.format("%.0f", total);
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Total", "/api/search/total" + totalS);
        if (searchedCourses.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(searchedCourses, httpHeaders, HttpStatus.OK);
    }
    
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAll() {
    	//return courseService.getAllCourses();
    	try {
    		List<Course> courses = new ArrayList<Course>();
    		courses = courseService.getAllCourses();
    		if (courses.isEmpty()) {
    			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    		}
    		return new ResponseEntity<>(courses, HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
      Optional<Course> courseData = courseRepository.findById(id);
      if (courseData.isPresent()) {
    	  return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    }
    
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    	Course addedCourse = courseService.add(course);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(addedCourse.getClass())
    			.toUri();
    	return ResponseEntity.created(uri).body(addedCourse);
    	/*HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("course", "/api/courses" + addedCourse.toString());
    	return new ResponseEntity<>(addedCourse, httpHeaders, HttpStatus.CREATED);*/
    }
}
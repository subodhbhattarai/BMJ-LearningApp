package com.bmj.learningapp;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bmj.learningapp.index.Indexer;
import com.bmj.learningapp.model.Course;
import com.bmj.learningapp.repository.CourseRepository;

@SpringBootApplication
public class LearningAppApplication {

	private static final Logger log = LoggerFactory.getLogger(LearningAppApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LearningAppApplication.class, args);
	}
	
	@Bean
    public ApplicationRunner initializeData(CourseRepository courseRepository) throws Exception {
        return (ApplicationArguments args) -> {
            List<Course> courses = Arrays.asList(
                    new Course("Introduction to mechanical ventilation", "5.00"),
                    new Course("Introduction to coronavirus disease 2019 (COVID-19)", "3.5"),
                    new Course("Clinical pointers: COVID-19 in primary care", "2.00"),
                    new Course("Clinical pointers: remote consultations in primary care", "1"),
                    new Course("Quick tips: introduction to asthma", ""),
                    new Course("Infection control - including basic personal protective equipment", "10.25"),
                    new Course("Introduction to testing for COVID-19", "7.50"),
                    new Course("Airways management: tracheal intubation", "2.00"),
                    new Course("Quick tips: proning in critical care", "2.50"),
                    new Course("Quick tips: introduction to asthma", "3.0")
            );
            courseRepository.saveAll(courses);
            
            // fetch all Courses
            log.info("Courses found with findAll():");
            log.info("-------------------------------");
            for (Course course : courseRepository.findAll()) {
              log.info(course.toString());
            }
            log.info(" COURSE LISTED ABOVE");
        };
    }
	
	@Bean
    public ApplicationRunner buildIndex(Indexer indexer) throws Exception {
        return (ApplicationArguments args) -> {
            indexer.indexPersistedData("com.bmj.learningapp.model.Course");
        };
    }

}

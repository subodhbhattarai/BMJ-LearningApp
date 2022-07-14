# BMJ-LearningApp
A SpringBoot restful web application in Java 11 with Maven, H2 db, Hibernate with Lucene.


The application exposes two endpoints, which should function as follows:

Endpoint 1: 
      An endpoint that accepts a text search term and returns all the records that contain said search term.

Endpoint 2:
     An endpoint that accepts a text search term and returns all the records that contain said search term, additionally it should return the sum of the hours from those records.


Data structure explanation:

The data is reminiscent of something that might be worked from in BMJ and consists of two parts, a value representing a time value in hours and a value representing a course title. 


Data: 
  
"5.00,Introduction to mechanical ventilation",
"3.5,Introduction to coronavirus disease 2019 (COVID-19)",
"2.00,Clinical pointers: COVID-19 in primary care",
"1,Clinical pointers: remote consultations in primary care",
"Quick tips: introduction to asthma",
"10.25,Infection control - including basic personal protective equipment",
"7.50,Introduction to testing for COVID-19",
"2.00,Airways management: tracheal intubation",
"2.50,Quick tips: proning in critical care",
"3.0,Quick tips: introduction to asthma"

To run the application via command line or IDE as:

      mvn spring-boot:run

      - Application is available on port 8080 and accessible via: 
            
            localhost:8080

To access the H2-console:
      
      localhost:8080/h2-console
      
      - The name of the db is generated during application start (mvn spring-boot run) and captured from the start-up logs.

To test the endpoints using cURL or Postman:

      - Entity:Course consists of 2 data: name and hours
      - DTO: SearchRequest consists of 3 request params: text, fields, limit

            text - accepts (multiple) the search pattern
            fields - accepts (multiple) fields where "text" is to be searched
            limit - displays the maximum number of possible results after the search is completed

      GET localhost:8080/api/search?text=Introduction&fields=name&limit=10 (search courses)
      GET localhost:8080/api/search/total?text=pointers&fields=name&limit=5 (search courses with total hours)
      
      - Bonus endpoints:
      GET localhost:8080/api/courses (for all courses)
      GET localhost:8080/api/courses/{id} (for course with courseId)
      POST localhost:8080/api/courses (to create a new course)
            - JSON body: { "name" : "New course", "hours" : "1.5" }

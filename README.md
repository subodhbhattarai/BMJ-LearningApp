# BMJ-LearningApp
A SpringBoot restful web application in Java 11 


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

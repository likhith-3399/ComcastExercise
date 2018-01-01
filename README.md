[![Build Status](http://54.82.248.248:8080/job/test/badge/icon)](http://54.82.248.248:8080/job/test/)

# ComcastExercise
Comcast Coding Challange
	
	ComcastExercise is a java, springboot application which has several Rest API's exposed each having a different functionality as described below.


### Pre-requisite
- Supports JDK 1.8 and Spring and supports development in java, build system is maven
- Suggested to used IntelliJ IDEA or Eclipse to compile and execute


## Technologies/ Tools/ Services Used:
- Java 8
- Maven
- SpringBoot
- Restful Webservices
- JUnits
- Java Persistence API
- Apache Logging


### Steps to Build and Deploy in Local machine
```
mvn clean install
Run the application, by adding the main class to Run Configuration.
If you want to run the application from command line, run the below command.
	mvn spring-boot:run
GoTo http://localhost:8090/voting/ping for ping page.
```


### Rest API's
*	Ping
```
>	URL: http://localhost:8080/service/ping



*	Print Fibonacci Series for given number N
```
>	URL: http://localhost:8080/service/ping


*	Deadlock Scenario
```
>	URL: http://localhost:8080/service/ping


*	HSQLDB Insert a Record
```
>	URL: http://localhost:8080/service/insertMovieDetails



*	HSQLDB View All Records
```
>	URL: http://localhost:8080/service/viewAllMovieDetails


*	HSQLDB View Records with Id
```
>	URL: http://localhost:8080/service/viewMovieDetailsByMovieId/{movieId}


*	HSQLDB View Records with Name
```
>	URL: http://localhost:8080/service/viewMovieDetailsByMovieName/{movieName}


*	HSQLDB Delete Records with Name
```
>	URL: http://localhost:8080/service/deleteSingleMovieDetails/{movieName}


*	HSQLDB Delete All Records
```
>	URL: http://localhost:8080/service/deleteAllMovieDetails


*	Using Rest Template to consume external Webservice
```
>	URL: http://localhost:8080/service/consumeExternalRestAPI






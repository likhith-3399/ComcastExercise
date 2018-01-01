[![Build Status](http://54.82.248.248:8080/job/test/badge/icon)](http://54.82.248.248:8080/job/test/)

# ComcastExercise

ComcastExercise is a java, springboot application which has several Rest API's exposed each having a different functionality as described below.

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- Supports JDK 1.8 and Spring and supports development in java, build system is maven
- Suggested to used IntelliJ IDEA or Eclipse to compile and execute


### Installing

A step by step series of examples that tell you how to get a development env running in local machine

1. Clone the repo from GitHub using gitcli command,

```
git clone https://github.com/likhith-3399/ComcastExercise.git
```

2. Build the application
```
mvn clean install (runs junit tests)
mvn clean install -DskipTests (to ignore/ skip junits)
```

3. Deploy/ Run the Application
```
Run the application, by adding the main class to Run Configuration.
If you want to run the application from command line, run the below command.
	mvn spring-boot:run
```

4. Once, the application is started, GoTo the url below to check if its up.
>	URL: http://localhost:8080/service/ping


### Rest API's with its Request Method enclosed in ()
You can use any Rest Client, I've used Postman, use this link to download https://www.getpostman.com/

#### Ping (GET)
>	URL: http://localhost:8080/service/ping
```
Example:
   Request:
	http://localhost:8080/service/ping
   Response:
   	Service is up !!! ... Running Version : 0.0.1-SNAPSHOT
```


####	Print Fibonacci Series for given number 'N' (GET)
>	URL: http://localhost:8080/service/printFibonacciNumbers/{number}
```
Example:
   Request:
	http://localhost:8080/service/printFibonacciNumbers/15
   Response:
   	[0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610]
   	
```


####	Deadlock Scenario (GET)
>	URL: http://localhost:8080/service/dealLockScenario/{timeOut}
```
Example:
   Request:
	http://localhost:8080/service/dealLockScenario/5000
   Response:
   	
   	
```


####	Insert a Record (POST)
>	URL: http://localhost:8080/service/insertMovieDetails

Insert a movie record in the Json format shown below,
```
Example:
   Request:
	URL: http://localhost:8080/service/insertMovieDetails
	BODY:	{
			"movieName": "SuperMan",
			"movieRating": "3"
		}
   Respomse:
   	Record Successfully Created for movie :SuperMan
```


####	View All Records (GET)
>	URL: http://localhost:8080/service/viewAllMovieDetails
```
Example
   Request:
	URL: http://localhost:8080/service/viewAllMovieDetails/
   
   Response:
	[
	    {
		"id": 1,
		"movieName": "SuperMan",
		"movieRating": 3
	    }
	]

```


####	View Records with Id (GET)
>	URL: http://localhost:8080/service/viewMovieDetailsByMovieId/{movieId}
```
Example:
    Request:
	 URL: http://localhost:8080/service/viewMovieDetailsByMovieId/1
    
    Response:
   	{
    	"id": 1,
    	"movieName": "SuperMan",
    	"movieRating": 3
	}
   	
```


####	View Records with Name (GET)
>	URL: http://localhost:8080/service/viewMovieDetailsByMovieName/{movieName}
```
Example:
    Request:
	 URL: http://localhost:8080/service/viewMovieDetailsByMovieName/SuperMan
    
    Response:
   	[
    		[
        		1,
        		"SuperMan",
        		3
    		]
	]
```


####	Delete Records with Name (GET)
>	URL: http://localhost:8080/service/deleteSingleMovieDetails/{movieName}
```
Example:
    Request:
	 URL: http://localhost:8080/service/deleteSingleMovieDetails/SuperMan
    
    Response:
	Record with MOVIE_NAME :SuperMan, DELETED.
		-If the Movie Name passed exists in HSQLDB the,	
		
	No Record exists with MOVIE_NAME :SuperMans.So, NO record Deleted !!!
		-If the Movie Name passed doesn't exist in HSQLDB the,	
```


####	Delete All Records (GET)
>	URL: http://localhost:8080/service/deleteAllMovieDetails
```
Example:
    Request:
	 URL: http://localhost:8080/service/deleteAllMovieDetails
   
    Response:
   	All records DELETED Successfully.
```


####	Using Rest Template to consume external Webservice (GET)
>	URL: http://localhost:8080/service/consumeExternalRestAPI
```
Example:
    Request:
	 URL: http://localhost:8080/service/consumeExternalRestAPI
    Response:
   	{
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas       totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```



### Built With

* [SpringBoot](https://spring.io/guides/gs/spring-boot/) - The Web framework
* [Maven](https://maven.apache.org/) - Dependency Management


### Versioning

For the versions available, see the [tags on this repository](https://github.com/likhith-3399/ComcastExercise/releases). 


### Authors & Contributing

* **Likhith Matta**

See also the list of [contributors](https://github.com/likhith-3399/ComcastExercise/contributors) who participated in this project.

package com.comcast.coding.test.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.coding.test.common.Post;
import com.comcast.coding.test.persistence.entity.MovieDetailsEntity;
import com.comcast.coding.test.service.MovieDetailsService;
import com.comcast.coding.test.utilitys.DeadLockScenario;
import com.comcast.coding.test.utilitys.RestTemplateScenario;
import com.comcast.coding.test.utilitys.Utils;

@RequestMapping("/service")
@RestController
public class AppController {

	@Value("${app.version}")
	private String appVersion;

	@Autowired
	private MovieDetailsService movieDetailsService;

	@Autowired
	private DeadLockScenario deadLockScenario;

	@Autowired
	private RestTemplateScenario restTemplateScenario;

	private static final Logger logger = LogManager.getLogger(AppController.class);

	/*
	 * Generic Health check method for Application
	 */
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> ping() {
		logger.info(" Method: ping(), Stage: Started");

		StringBuilder responseString = new StringBuilder();
		responseString.append("Service is up !!! ");
		responseString.append("... Running Version : ");
		responseString.append(appVersion);

		logger.info(" Method: ping(), Stage: Ended");
		return new ResponseEntity<String>(responseString.toString(), HttpStatus.OK);
	}

	/*
	 * Method to print the fibonacci numbers
	 */
	@RequestMapping(value = "/printFibonacciNumbers/{number}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<Long>> printFibonacciNumbers(@PathVariable("number") Long number) {
		logger.info(" Method: printFibonacciNumbers(), Stage: Started");
		List<Long> numberList = Utils.printFibonacciNumberWrapper(number);
		logger.info(" Method: printFibonacciNumbers(), Stage: Ended");
		return new ResponseEntity<List<Long>>(numberList, HttpStatus.OK);
	}

	/*
	 * Method to showcase the DealLock Scenario
	 */
	@RequestMapping(value = "/dealLockScenario/{timeOut}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> dealLockScenario(@PathVariable("timeOut") int timeOut) {
		logger.info(" Method: dealLockScenario(), Stage: Started");
		deadLockScenario.createDealLockScenario(timeOut);
		logger.info(" Method: dealLockScenario(), Stage: Ended");
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	/*
	 * Method to insert a row to the table MOVIE_DETAILS
	 */
	@RequestMapping(value = "/insertMovieDetails", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> insertMovieDetails(@RequestBody MovieDetailsEntity entity) {
		logger.info(" Method: insertMovieDetails(), Stage: Started");
		movieDetailsService.insertMovieDetails(entity);
		logger.info(" Method: insertMovieDetails(), Stage: Ended");
		return new ResponseEntity<String>("Record Successfully Created for movie :" + entity.getMovieName(),
				HttpStatus.CREATED);
	}

	/*
	 * Method to view all tuples in the MOVIE_DETAILS table
	 */
	@RequestMapping(value = "/viewAllMovieDetails", method = RequestMethod.GET)
	public @ResponseBody List<MovieDetailsEntity> viewAllMovieDetails() {
		logger.info(" Method: viewAllMovieDetails(), Stage: Started");
		List<MovieDetailsEntity> movieList = movieDetailsService.showAllMovieDetails();
		logger.info(" Method: viewAllMovieDetails(), Stage: Ended");
		return movieList;
	}

	/*
	 * Method to view all tuples By Movie Id in the MOVIE_DETAILS table
	 */
	@RequestMapping(value = "/viewMovieDetailsByMovieId/{movieId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Optional<MovieDetailsEntity>> viewMovieDetailsByMovieId(
			@PathVariable("movieId") Integer movieId) {
		logger.info(" Method: viewMovieDetailsByMovieId(), Stage: Started");
		Optional<MovieDetailsEntity> movieList = movieDetailsService.showMovieDetailsById(movieId);
		logger.info(" Method: viewMovieDetailsByMovieId(), Stage: Ended");
		return new ResponseEntity<Optional<MovieDetailsEntity>>(movieList, HttpStatus.CREATED);
	}

	/*
	 * Method to view all tuples By Movie Name in the MOVIE_DETAILS table
	 */
	@RequestMapping(value = "/viewMovieDetailsByMovieName/{movieName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<MovieDetailsEntity>> viewMovieDetailsByMovieName(
			@PathVariable("movieName") String movieName) {
		logger.info(" Method: viewMovieDetailsByMovieName(), Stage: Started");
		List<MovieDetailsEntity> movieList = movieDetailsService.showMovieDetailsByTitle(movieName);
		logger.info(" Method: viewMovieDetailsByMovieName(), Stage: Ended");
		return new ResponseEntity<List<MovieDetailsEntity>>(movieList, HttpStatus.FOUND);
	}

	/*
	 * Method to delete a row in the MOVIE_DETAILS table
	 */
	@RequestMapping(value = "/deleteSingleMovieDetails/{movieName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> deleteSingleMovieDetails(@PathVariable("movieName") String movieName) {
		logger.info(" Method: deleteSingleMovieDetails(), Stage: Started");
		String message = movieDetailsService.deleteSingleMovieDetails(movieName);
		logger.info(" Method: deleteSingleMovieDetails(), Stage: Ended");
		return new ResponseEntity<String>(message, HttpStatus.FOUND);
	}

	/*
	 * Method to delete all tuples in the table MOVIE_DETAILS
	 */
	@RequestMapping(value = "/deleteAllMovieDetails", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> deleteAllMovieDetails() {
		logger.info(" Method: deleteAllMovieDetails(), Stage: Started");
		movieDetailsService.deleteAllMovieDetails();
		logger.info(" Method: deleteAllMovieDetails(), Stage: Ended");
		return new ResponseEntity<String>("All records DELETED Successfully.", HttpStatus.OK);
	}

	/*
	 * Wrapper Method to call the external REST API
	 */
	@RequestMapping(value = "/consumeExternalRestAPI", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<Post> consumeExternalRestAPI() {
		logger.info(" Method: consumeExternalRestAPI(), Stage: Started");
		Post message = restTemplateScenario.consumeRestAPI();
		logger.info(" Method: consumeExternalRestAPI(), Stage: Ended");
		return new ResponseEntity<Post>(message, HttpStatus.OK);
	}
	
	@RequestMapping("/*")
    public String home(){
        return "<center>\r\n" + 
        		"    <h1> 404 - Page Not Found !!!</h1>\r\n" + 
        		"    <h3> ************** SORRY ! THE PAGE YOU ARE LOOKING FOR IS NOT AVAILABLE*************</h3>\r\n" + 
        		"</center>";
    }

}

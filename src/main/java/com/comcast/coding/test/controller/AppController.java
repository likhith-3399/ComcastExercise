package com.comcast.coding.test.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.coding.test.persistence.entity.MovieDetailsEntity;
import com.comcast.coding.test.persistence.repositories.MovieDetailsRepository;


@RestController
public class AppController {

    @Value("${app.version}")
    private String appVersion;
    
    private static final Logger logger = LogManager.getLogger(AppController.class);
	   
	private final MovieDetailsRepository movieDetailsRepository;
	AppController(MovieDetailsRepository movieDetailsRepository){
		this.movieDetailsRepository = movieDetailsRepository;
		System.out.println(this.movieDetailsRepository);
	}
	
	/*
    *  Generic Health check method for Application
    * */
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> ping() {
        logger.info(" Method: ping(), Stage: Started");

        StringBuilder responseString = new StringBuilder();
        responseString.append("Service is up !!! ");
        responseString.append("... Running Version : ");
        responseString.append(appVersion);

        logger.info(" Method: ping(), Stage: Ended");
        return new ResponseEntity<String>(responseString.toString(), HttpStatus.OK);
    }
	
	@RequestMapping("/sample")
	public void getSample() {
		System.out.println("you have hit the end point");
		MovieDetailsEntity movie = new MovieDetailsEntity();
		movie.setMovieName("Suresh");
		movie.setMovieRating(5);
		movieDetailsRepository.save(movie);
		
		System.out.println("saved movie..");
		
		List<MovieDetailsEntity> list = movieDetailsRepository.findAll();
		
		System.out.println(list);
		
	}
}

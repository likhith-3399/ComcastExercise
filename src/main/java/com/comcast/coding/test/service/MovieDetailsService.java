package com.comcast.coding.test.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.comcast.coding.test.exception.ExerciseRuntimeException;
import com.comcast.coding.test.persistence.entity.MovieDetailsEntity;
import com.comcast.coding.test.persistence.repositories.MovieDetailsRepository;
import com.comcast.coding.test.service.MovieDetailsService;

@Service
public class MovieDetailsService {

    private static final Logger logger = LogManager.getLogger(MovieDetailsService.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final MovieDetailsRepository movieDetailsRepository;
    
    MovieDetailsService(MovieDetailsRepository movieDetailsRepository){
		this.movieDetailsRepository = movieDetailsRepository;
	}
	
	public void insertMovieDetails(MovieDetailsEntity movieDetailsEntity) {
        logger.info(" Method: insertMovieDetails(), Stage: Started");
        try {
        		movieDetailsRepository.save(movieDetailsEntity);
        } catch (Exception exception) {
        		logger.error("Error while saving the record for movie : "+movieDetailsEntity.getMovieName());
			throw new ExerciseRuntimeException("Exception while saving the record ", exception);
        }
        logger.info(" Method: insertMovieDetails(), Stage: Ended");
	}
	
	@SuppressWarnings("unchecked")
	public List<MovieDetailsEntity> showMovieDetailsByTitle(String movieName) {
		logger.info(" Method: showMovieDetailsByTitle(), Stage: Started");
		List<MovieDetailsEntity> movieDetailsList = null;
		
		try {
			movieDetailsList = (List<MovieDetailsEntity>)entityManager
					.createNativeQuery("SELECT * FROM MOVIE_DETAILS WHERE MOVIE_NAME = ?1")
					.setParameter(1, movieName)
					.getResultList();
		} catch (Exception exception) {
			logger.error("Error while getting the record for movie : "+movieName);
			throw new ExerciseRuntimeException("Exception while getting the record ", exception);
		}
        logger.info(" Method: showMovieDetailsByTitle(), Stage: Ended");
		return movieDetailsList;
	  }
	
	public Optional<MovieDetailsEntity> showMovieDetailsById(Integer id) {
		logger.info(" Method: showMovieDetailsById(), Stage: Started");
		Optional<MovieDetailsEntity> movieDetailsList = null;
		try {
			 movieDetailsList = movieDetailsRepository.findById(id);
		} catch (Exception exception) {
			logger.error(" Error while showing movie details for Id: "+id);
			throw new ExerciseRuntimeException("Exception while showing the movie details", exception);
		}
        logger.info(" Method: showMovieDetailsById(), Stage: Ended");
		return movieDetailsList;
	  }

	public List<MovieDetailsEntity> showAllMovieDetails() {
		logger.info(" Method: showAllMovieDetails(), Stage: Started");
		List<MovieDetailsEntity> movieDetailsList = null;
		try {
			 movieDetailsList = movieDetailsRepository.findAll();
		} catch (Exception exception) {
			logger.error(" Error while showing movie details !!!");
			throw new ExerciseRuntimeException("Exception while showing the movie details", exception);
		}
        logger.info(" Method: showAllMovieDetails(), Stage: Ended");
		return movieDetailsList;
	}

	@SuppressWarnings({ "unchecked"})
	@Transactional
	public String deleteSingleMovieDetails(String movieName) {
		logger.info(" Method: deleteSingleMovieDetails(), Stage: Started");
		String message = null;
		try {
			 List<MovieDetailsEntity> movieDetailsList = (List<MovieDetailsEntity>)entityManager
					 	.createNativeQuery("SELECT * FROM MOVIE_DETAILS WHERE MOVIE_NAME = ?1")
						.setParameter(1, movieName)
						.getResultList();
			 Assert.notNull(movieDetailsList, "Movie :"+movieName+" not Found !!!");

			 	if (!movieDetailsList.isEmpty()) {
					Query jpqlQuery = entityManager.createNativeQuery("DELETE FROM MOVIE_DETAILS WHERE MOVIE_NAME = ?1");
					jpqlQuery.setParameter(1, movieName);
					jpqlQuery.executeUpdate();				
					message = (new StringBuilder()).append("Record with MOVIE_NAME :")
							.append(movieName)
							.append(", DELETED.")
							.toString(); 
				} else {
					message = (new StringBuilder()).append("No Record exists with MOVIE_NAME :")
							.append(movieName)
							.append(".So, NO record Deleted !!!").toString();  
				}			 				 
		} catch (Exception exception) {
			logger.error(" Error while deleting the movie details for movie :"+movieName);
			throw new ExerciseRuntimeException("Exception while deleting the movie details", exception);
		}
        logger.info(" Method: deleteSingleMovieDetails(), Stage: Ended");
        return message;
	}

	public void deleteAllMovieDetails() {
		logger.info(" Method: deleteAllMovieDetails(), Stage: Started");
		try {
			movieDetailsRepository.deleteAll();
		} catch (Exception exception) {
			logger.error(" Error while deleting All Movie Details !!!");
			throw new ExerciseRuntimeException("Exception while deleting All Movie Details ", exception);
		}
        logger.info(" Method: deleteAllMovieDetails(), Stage: Ended");		
	}

}

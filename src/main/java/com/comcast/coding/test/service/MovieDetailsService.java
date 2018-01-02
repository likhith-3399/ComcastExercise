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

import com.comcast.coding.test.persistence.entity.MovieDetailsEntity;
import com.comcast.coding.test.persistence.repositories.MovieDetailsRepository;
import com.comcast.coding.test.service.MovieDetailsService;

/**
 * Service class which handles all the transactions to HSQLDB
 * 
 * @author likhithkumarmatta
 *
 */
@Service
public class MovieDetailsService {

	private static final Logger logger = LogManager.getLogger(MovieDetailsService.class);

	@PersistenceContext
	private EntityManager entityManager;

	private final MovieDetailsRepository movieDetailsRepository;

	MovieDetailsService(MovieDetailsRepository movieDetailsRepository) {
		this.movieDetailsRepository = movieDetailsRepository;
	}

	/**
	 * Method to insert a Movie Details record to DB
	 *
	 * @param movieDetailsEntity
	 * @return
	 */
	public String insertMovieDetails(MovieDetailsEntity movieDetailsEntity) {
		logger.info(" Method: insertMovieDetails(), Stage: Started");
		MovieDetailsEntity storedMovieDetails = movieDetailsRepository.save(movieDetailsEntity);
		logger.info(" Method: insertMovieDetails(), Stage: Ended");
		return storedMovieDetails.getMovieName();
	}

	/**
	 * Method to show all the Movie Details for the given Movie Title
	 * @param movieName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MovieDetailsEntity> showMovieDetailsByTitle(String movieName) {
		logger.info(" Method: showMovieDetailsByTitle(), Stage: Started");
		List<MovieDetailsEntity> movieDetailsList = null;

		movieDetailsList = (List<MovieDetailsEntity>) entityManager
				.createNativeQuery("SELECT * FROM MOVIE_DETAILS WHERE MOVIE_NAME = ?1").setParameter(1, movieName)
				.getResultList();
		logger.info(" Method: showMovieDetailsByTitle(), Stage: Ended");
		return movieDetailsList;
	}

	/**
	 * Method to show the Movie Details for the given Id
	 * 
	 * @param id
	 * @return
	 */
	public Optional<MovieDetailsEntity> showMovieDetailsById(Integer id) {
		logger.info(" Method: showMovieDetailsById(), Stage: Started");
		Optional<MovieDetailsEntity> movieDetailsList = null;
		movieDetailsList = movieDetailsRepository.findById(id);
		logger.info(" Method: showMovieDetailsById(), Stage: Ended");
		return movieDetailsList;
	}

	/**
	 *  Mthod to show all the Records of Movie Details
	 * @return
	 */
	public List<MovieDetailsEntity> showAllMovieDetails() {
		logger.info(" Method: showAllMovieDetails(), Stage: Started");
		List<MovieDetailsEntity> movieDetailsList = null;
		movieDetailsList = movieDetailsRepository.findAll();
		logger.info(" Method: showAllMovieDetails(), Stage: Ended");
		return movieDetailsList;
	}

	/**
	 *  Method to delete Movie Details for the given Movie Name 
	 *  
	 * @param movieName
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public String deleteSingleMovieDetails(String movieName) {
		logger.info(" Method: deleteSingleMovieDetails(), Stage: Started");
		String message = null;
			List<MovieDetailsEntity> movieDetailsList = (List<MovieDetailsEntity>) entityManager
					.createNativeQuery("SELECT * FROM MOVIE_DETAILS WHERE MOVIE_NAME = ?1").setParameter(1, movieName)
					.getResultList();
			Assert.notNull(movieDetailsList, "Movie :" + movieName + " not Found !!!");

			if (!movieDetailsList.isEmpty()) {
				Query jpqlQuery = entityManager.createNativeQuery("DELETE FROM MOVIE_DETAILS WHERE MOVIE_NAME = ?1");
				jpqlQuery.setParameter(1, movieName);
				jpqlQuery.executeUpdate();
				message = (new StringBuilder()).append("Record with MOVIE_NAME :").append(movieName)
						.append(", DELETED.").toString();
			} else {
				message = (new StringBuilder()).append("No Record exists with MOVIE_NAME :").append(movieName)
						.append(".So, NO record Deleted !!!").toString();
			}
		logger.info(" Method: deleteSingleMovieDetails(), Stage: Ended");
		return message;
	}

	/**
	 *  Method to delete all the Movie Details from DB
	 */
	public void deleteAllMovieDetails() {
		logger.info(" Method: deleteAllMovieDetails(), Stage: Started");
		movieDetailsRepository.deleteAll();
		logger.info(" Method: deleteAllMovieDetails(), Stage: Ended");
	}

}

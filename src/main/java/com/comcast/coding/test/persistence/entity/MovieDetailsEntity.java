package com.comcast.coding.test.persistence.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity class
 * 
 * @author likhithkumarmatta
 *
 */
@Entity
@Table(name = "MOVIE_DETAILS")
public class MovieDetailsEntity implements Serializable{

    /**
	 *  Generated serialVersionUID
	 */
	private static final long serialVersionUID = 3265323433550332607L;

	public static final String INTEGER="INTEGER";

    @Id
    @Column(name = "MOVIE_ID",columnDefinition=INTEGER)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "MOVIE_NAME", nullable = false, length = 100)
    private String movieName;

    @Column(name = "MOVIE_RATING", nullable = false)
    private Float movieRating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Float getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Float movieRating) {
        this.movieRating = movieRating;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this)
                .append("Id: ", this.id)
                .append("movieName: ", this.movieName)
                .append("movieRating: ", this.movieRating)
                .toString();
    }
}

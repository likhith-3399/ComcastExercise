package com.comcast.coding.test.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comcast.coding.test.persistence.entity.MovieDetailsEntity;

/**
 * Created by NUS889 on 12/26/2017.
 */
@Repository
        //(value = "movieDetailsRepository")
public interface MovieDetailsRepository extends JpaRepository<MovieDetailsEntity, Integer> {


}

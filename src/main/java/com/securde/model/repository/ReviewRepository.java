package com.securde.model.repository;

import com.securde.model.reservable.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by kevin on 7/5/2017.
 */
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.user.userId = :userId")
    ArrayList<Review> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.text.textId = :textId")
    ArrayList<Review> findByTextId(@Param("textId") Integer textId);
}

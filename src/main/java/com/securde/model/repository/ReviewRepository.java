package com.securde.model.repository;

import com.securde.model.reservable.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by kevin on 7/5/2017.
 */
public interface ReviewRepository extends CrudRepository<Review, Integer> {

    ArrayList<Review> findByUserId(Integer userId);
    ArrayList<Review> findByTextId(Integer textId);
}

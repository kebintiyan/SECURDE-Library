package com.securde.model.repository;

import com.securde.model.reservable.Text;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by kevin on 6/25/2017.
 */
public interface TextRepository extends CrudRepository <Text, Integer> {

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.author LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.location LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.title LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.publisher LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.year LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.tags LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> search(@Param("searchParam") String searchParam);
    ArrayList<Text> findByTitle(String title);
}

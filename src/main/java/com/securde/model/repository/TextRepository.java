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

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.type LIKE '%BOOK%'")
    ArrayList<Text> findAllBooks();

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.type LIKE '%THESIS%'")
    ArrayList<Text> findAllThesis();

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.type LIKE '%MAGAZINE%'")
    ArrayList<Text> findAllMagazines();

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.title LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> findByTitleContaining(@Param("searchParam") String title);

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.author LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> findByAuthorContaining(@Param("searchParam") String author);

    @Query("SELECT DISTINCT t.author " +
            "from Text t")
    ArrayList<String> findDistinctAuthors();


    @Query("SELECT DISTINCT t.publisher " +
            "from Text t ")
    ArrayList<String> findDistinctPublishers();

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.publisher LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> findByPublisherContaining(@Param("searchParam") String publisher);

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.title LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.author LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> findByTitleOrAuthorContaining(@Param("searchParam") String title);

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.title LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.publisher LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> findByTitleOrPublisherContaining(@Param("searchParam") String title);

    @Query("SELECT t " +
            "from Text t " +
            "WHERE t.author LIKE LOWER(CONCAT('%',:searchParam, '%')) OR " +
            "t.publisher LIKE LOWER(CONCAT('%',:searchParam, '%'))")
    ArrayList<Text> findByAuthorOrPublisherContaining(@Param("searchParam") String title);
}

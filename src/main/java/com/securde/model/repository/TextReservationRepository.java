package com.securde.model.repository;

import com.securde.model.reservation.TextReservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by kevin on 6/25/2017.
 */
public interface TextReservationRepository extends CrudRepository<TextReservation, Integer> {

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.user.userId = :userId")
    ArrayList<TextReservation> findTextReservationsByUserId(@Param("userId") int userId);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.text.textId = :textId")
    ArrayList<TextReservation> findTextReservationsByTextId(@Param("textId") int textId);

}

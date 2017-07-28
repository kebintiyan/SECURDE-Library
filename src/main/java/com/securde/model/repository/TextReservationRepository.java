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
    ArrayList<TextReservation> findTextReservationsByUserId(@Param("userId") Integer userId);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.text.textId = :textId")
    ArrayList<TextReservation> findTextReservationsByTextId(@Param("textId") Integer textId);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.user.userId = :userId " +
                "AND tr.text.textId = :textId")
    ArrayList<TextReservation> findTextReservationsByUserIdAndTextId(@Param("userId") Integer userId, @Param("textId") Integer textId);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.user.userId = :userId " +
                "AND tr.text.textId = :textId AND tr.reservationEndDate < :endDate")
    ArrayList<TextReservation> findPreviousTextReservationsByUserIdAndTextId(
            @Param("userId") Integer userId,
            @Param("textId") Integer textId,
            @Param("endDate") String endDate);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.user.userId = :userId " +
            "AND tr.text.textId = :textId AND tr.reservationStartDate >= :date OR tr.reservationEndDate >= :date")
    ArrayList<TextReservation> findTextReservationsByUserIdAndTextIdFromDate(
            @Param("userId") Integer userId,
            @Param("textId") Integer textId,
            @Param("date") String date);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.text.textId = :textId AND " +
            "tr.reservationStartDate >= :date OR tr.reservationEndDate >= :date")
    ArrayList<TextReservation> findTextReservationsByTextIdFromDate(
            @Param("textId") Integer textId,
            @Param("date") String date);

    @Query("SELECT tr " +
            "FROM TextReservation tr " +
            "WHERE tr.reservationStartDate >= :date OR tr.reservationEndDate >= :date")
    ArrayList<TextReservation> findTextReservationsFromDate(@Param("date") String date);
}

package com.securde.model.repository;

import com.securde.model.reservation.RoomReservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * Created by kevin on 6/25/2017.
 */
public interface RoomReservationRepository extends CrudRepository<RoomReservation, Integer> {

    @Query("SELECT rr " +
            "FROM RoomReservation rr " +
            "WHERE rr.reservationDate = :date")
    ArrayList<RoomReservation> findTextReservationsByDate(@Param("date") String date);

}

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
    ArrayList<RoomReservation> findRoomReservationsByDate(@Param("date") String date);

    @Query("SELECT rr " +
            "FROM RoomReservation rr " +
            "WHERE rr.user.userId = :userId AND " +
                "rr.reservationDate >= :date")
    ArrayList<RoomReservation> findRoomReservationsByUserFromDate(@Param("userId") Integer userId, @Param("date") String date);

    @Query("SELECT rr " +
            "FROM RoomReservation rr " +
            "WHERE rr.room.roomId = :roomId AND " +
                "rr.reservationStartTime = :startTime AND " +
                "rr.reservationDate = :date")
    ArrayList<RoomReservation> findRoomReservationByRoomIdFromStartTimeAndDate(@Param("roomId") Integer userId, @Param("startTime") String startTime, @Param("date") String date);
}

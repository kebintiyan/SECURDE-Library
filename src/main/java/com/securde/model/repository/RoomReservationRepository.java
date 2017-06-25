package com.securde.model.repository;

import com.securde.model.reservation.RoomReservation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kevin on 6/25/2017.
 */
public interface RoomReservationRepository extends CrudRepository<RoomReservation, Integer> {
}

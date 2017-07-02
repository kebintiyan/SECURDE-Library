package com.securde.model.repository;

import com.securde.model.reservable.Text;
import com.securde.model.reservation.TextReservation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kevin on 6/25/2017.
 */
public interface TextReservationRepository extends CrudRepository<TextReservation, Integer> {

    //TextReservation findByUsername(String username);

}

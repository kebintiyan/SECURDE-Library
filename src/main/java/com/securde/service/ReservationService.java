package com.securde.service;

import com.securde.model.repository.RoomReservationRepository;
import com.securde.model.repository.TextReservationRepository;
import com.securde.model.reservation.TextReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by patricktobias on 03/07/2017.
 */
@Service("reservationService")
public class ReservationService {

    @Autowired
    TextReservationRepository textReservationRepository;

    public ArrayList<TextReservation> findTextReservationsByUserId (int id) {
        return textReservationRepository.findTextReservationsByUserId(id);
    }

    public ArrayList<TextReservation> findTextReservationByTextId (int id) {
        return textReservationRepository.findTextReservationsByTextId(id);
    }

    public void saveTextReservation (TextReservation textReservation) {
        textReservationRepository.save(textReservation);
    }

}

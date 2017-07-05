package com.securde.service;

import com.securde.model.repository.RoomReservationRepository;
import com.securde.model.repository.TextReservationRepository;
import com.securde.model.reservation.RoomReservation;
import com.securde.model.reservation.TextReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by patricktobias on 03/07/2017.
 */
@Service("reservationService")
public class ReservationService {

    @Autowired
    TextReservationRepository textReservationRepository;

    @Autowired
    RoomReservationRepository roomReservationRepository;

    public ArrayList<TextReservation> findTextReservationsByUserId (int id) {
        return textReservationRepository.findTextReservationsByUserId(id);
    }

    public ArrayList<TextReservation> findTextReservationByTextId (int id) {
        return textReservationRepository.findTextReservationsByTextId(id);
    }

    public void saveTextReservation (TextReservation textReservation) {
        textReservationRepository.save(textReservation);
    }

    public void saveRoomReservation (RoomReservation roomReservation) {
        roomReservationRepository.save(roomReservation);
    }

    public ArrayList<TextReservation> getReservationsByUserIdAndTextId(Integer userId, Integer textId) {
        return textReservationRepository.findTextReservationsByUserIdAndTextId(userId, textId);
    }

    public ArrayList<TextReservation> getReservationsByUserIdAndTextIdFromDate(Integer userId, Integer textId, String date) {
        return textReservationRepository.findTextReservationsByUserIdAndTextIdFromDate(userId, textId, date);
    }

    public ArrayList<TextReservation> getPreviousReservationsByUserIdAndTextId(Integer userId, Integer textId) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return textReservationRepository.findPreviousTextReservationsByUserIdAndTextId(userId, textId, sdf.format(date));
    }

    public ArrayList<RoomReservation> getRoomReservationsByDate(String date) {
        return roomReservationRepository.findRoomReservationsByDate(date);
    }

    public ArrayList<RoomReservation> getRoomReservationsByUserFromDate(Integer userId, String date) {
        return roomReservationRepository.findRoomReservationsByUserFromDate(userId, date);
    }

}

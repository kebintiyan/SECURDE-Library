package com.securde.service;

import com.securde.model.repository.RoomRepository;
import com.securde.model.repository.TextRepository;
import com.securde.model.reservable.Room;
import com.securde.model.reservable.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by kevin on 6/25/2017.
 */

@Service("reservableService")
public class ReservableService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    TextRepository textRepository;

    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        Iterable<Room> roomIterable = roomRepository.findAll();

        roomIterable.forEach(rooms::add);

        return rooms;
    }

    public ArrayList<Text> getAllTexts() {
        ArrayList<Text> texts = new ArrayList<>();
        Iterable<Text> textIterable = textRepository.findAll();

        textIterable.forEach(texts::add);

        return texts;
    }

    public ArrayList<Text> searchText(String searchParam) {
        return textRepository.search(searchParam);
    }

    public void saveText(Text text) {
        textRepository.save(text);
    }
}
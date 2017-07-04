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

    public ArrayList<Text> findTextByTitleContaining(String title) {
        return textRepository.findByTitleContaining(title);
    }

    public ArrayList<Text> findTextByAuthorContaining(String author) {
        return textRepository.findByAuthorContaining(author);
    }

    public ArrayList<Text> findTextByPublisherContaining(String publisher) {
        return textRepository.findByPublisherContaining(publisher);
    }

    public ArrayList<Text> findTextByTitleOrAuthorContaining(String titleorAuthor) {
        return textRepository.findByTitleOrAuthorContaining(titleorAuthor);
    }

    public ArrayList<Text> findTextByTitleOrPublisherContaining(String titleorPublisher) {
        return textRepository.findByTitleOrPublisherContaining(titleorPublisher);
    }

    public ArrayList<Text> findTextByAuthorOrPublisherContaining(String authorOrPublisher) {
        return textRepository.findByAuthorOrPublisherContaining(authorOrPublisher);
    }

    public ArrayList<String> findDistinctAuthors(){
        return textRepository.findDistinctAuthors();
    }

    public ArrayList<Text> findAllBooks(){
        return textRepository.findAllBooks();
    }

    public ArrayList<Text> findAllThesis(){
        return textRepository.findAllThesis();
    }

    public ArrayList<Text> findAllMagazines(){
        return textRepository.findAllMagazines();
    }

    public ArrayList<String> findDistinctPublishers(){
        return textRepository.findDistinctPublishers();
    }

    public Text getText(Integer id) {
        return textRepository.findOne(id);
    }

    public void deleteText(Integer id) {
        textRepository.delete(id);
    }

    public Room getRoom(Integer id) {
        return roomRepository.findOne(id);
    }

}

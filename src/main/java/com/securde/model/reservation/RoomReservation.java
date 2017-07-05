package com.securde.model.reservation;

import com.securde.model.reservable.Room;

import javax.persistence.*;

/**
 * Created by kevin on 6/25/2017.
 */

@Entity
@Table(name = "room_reservations")
public class RoomReservation extends Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomReservationId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String reservationDate;
    private String reservationStartTime;
    private String reservationEndTime;

    public Integer getRoomReservationId() {
        return roomReservationId;
    }

    public RoomReservation setRoomReservationId(Integer roomReservationId) {
        this.roomReservationId = roomReservationId;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public RoomReservation setRoom(Room room) {
        this.room = room;
        return this;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public RoomReservation setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public String getReservationStartTime() {
        return reservationStartTime;
    }

    public RoomReservation setReservationStartTime(String reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
        return this;
    }

    public String getReservationEndTime() {
        return reservationEndTime;
    }

    public RoomReservation setReservationEndTime(String reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
        return this;
    }

    public static class RoomIDAndStartTime {

        Integer id;
        String time;

        public RoomIDAndStartTime() {

        }

        public RoomIDAndStartTime(Integer id, String time) {
            this.id = id;
            this.time = time;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }
}

package com.securde.model.reservable;

import javax.persistence.*;

/**
 * Created by kevin on 6/21/2017.
 */

@Entity
@Table(name = "rooms")
public class Room extends Reservable {

    public enum Status {
        RESERVED,
        AVAILABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomId;

    private String name;
    private Status status;

    public Integer getRoomId() {
        return roomId;
    }

    public Room setRoomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Room setStatus(Status status) {
        this.status = status;
        return this;
    }
}

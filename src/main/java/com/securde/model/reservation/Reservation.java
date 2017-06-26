package com.securde.model.reservation;

import com.securde.model.account.User;
import com.securde.model.reservable.Text;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Created by kevin on 6/25/2017.
 */

@MappedSuperclass
public abstract class Reservation {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public Reservation setUser(User user) {
        this.user = user;
        return this;
    }
}

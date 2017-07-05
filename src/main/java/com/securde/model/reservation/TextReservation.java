package com.securde.model.reservation;

import com.securde.model.account.User;
import com.securde.model.reservable.Text;

import javax.persistence.*;

/**
 * Created by kevin on 6/25/2017.
 */

@Entity
@Table(name="text_reservations")
public class TextReservation extends Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer textReservationId;

    @ManyToOne
    @JoinColumn(name = "text_id")
    private Text text;

    private String reservationStartDate;
    private String reservationEndDate;

    public Integer getTextReservationId() {
        return textReservationId;
    }

    public TextReservation setTextReservationId(Integer textReservationId) {
        this.textReservationId = textReservationId;
        return this;
    }

    public Text getText() {
        return text;
    }

    public TextReservation setText(Text text) {
        this.text = text;
        return this;
    }

    public String getReservationStartDate() {
        return reservationStartDate;
    }

    public TextReservation setReservationStartDate(String reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
        return this;
    }

    public String getReservationEndDate() {
        return reservationEndDate;
    }

    public TextReservation setReservationEndDate(String reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
        return this;
    }
}

package com.securde.model.reservable;

import com.securde.model.account.User;

import javax.persistence.*;

/**
 * Created by kevin on 7/5/2017.
 */

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "text_id")
    private Text text;

    private Integer rating;

    private String reviewText;

    private String dateTimeReviewed;

    public Integer getReviewId() {
        return reviewId;
    }

    public Review setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Review setUser(User user) {
        this.user = user;
        return this;
    }

    public Text getText() {
        return text;
    }

    public Review setText(Text text) {
        this.text = text;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public Review setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Review setReviewText(String reviewText) {
        this.reviewText = reviewText;
        return this;
    }

    public String getDateTimeReviewed() {
        return dateTimeReviewed;
    }

    public Review setDateTimeReviewed(String dateTimeReviewed) {
        this.dateTimeReviewed = dateTimeReviewed;
        return this;
    }
}

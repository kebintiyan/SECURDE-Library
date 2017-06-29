package com.securde.model.reservable;

import javax.persistence.*;

/**
 * Created by kevin on 6/21/2017.
 */

// Class for Book and Magazine
@Entity
@Table(name="texts")
public class Text extends Reservable {

    public enum Status {
        OUT,
        RESERVED,
        AVAILABLE
    }

    public enum Type {
        BOOK,
        THESIS,
        MAGAZINE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer textId;

    private String title;
    private String location;
    private String author;
    private String publisher;
    private String year;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String tags;

    @Enumerated(EnumType.STRING)
    private Status status;

    public int getTextId() {
        return textId;
    }

    public Text setTextId(Integer textId) {
        this.textId = textId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Text setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Text setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Text setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Text setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Text setYear(String year) {
        this.year = year;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Text setType(Type type) {
        this.type = type;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public Text setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Text setStatus(Status status) {
        this.status = status;
        return this;
    }
}

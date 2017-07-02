package com.securde.model.reservable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import java.util.ArrayList;

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

    @SafeHtml
    @NotBlank(message = "Please input a valid title.")
    private String title;

    @SafeHtml
    @NotBlank(message = "Please input a valid location.")
    private String location;

    @SafeHtml
    @NotBlank(message = "Please input a valid author.")
    private String author;

    @SafeHtml
    @NotBlank(message = "Please input a valid publisher.")
    private String publisher;

    @SafeHtml
    @NotBlank(message = "Please input a valid year.")
    private String year;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    @Convert(converter = TagConverter.class)
    private ArrayList<String> tags;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Integer getTextId() {
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

    public Text setType(String type) {
        type = type.toLowerCase();

        if (type.equals("book")) {
            setType(Text.Type.BOOK);
        }
        else if (type.equals("thesis")) {
            setType(Text.Type.THESIS);
        }
        else if (type.equals("magazine")) {
            setType(Text.Type.MAGAZINE);
        }

        return this;
    }

    public ArrayList<String> getTags() {
        /*while(tags.size() <= 5) {
            tags.add("");
        }*/

        return tags;
    }

    public Text setTags(ArrayList<String> tags) {
        /*for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).trim().isEmpty()) {
                tags.remove(i);
                i--;
            }
        }*/

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

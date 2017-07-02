package com.securde.model.reservable;

import javax.persistence.Entity;

/**
 * Created by avggo on 7/2/2017.
 */

public class SearchParameters {

    private String search_parameter;

    private Boolean author;
    private Boolean title;
    private Boolean publisher;

    public Boolean getAuthor() {
        return author;
    }

    public void setAuthor(Boolean author) {
        this.author = author;
    }

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }

    public Boolean getPublisher() {
        return publisher;
    }

    public void setPublisher(Boolean publisher) {
        this.publisher = publisher;
    }

    public String getSearch_parameter() {
        return search_parameter;
    }

    public void setSearch_parameter(String search_parameter) {
        this.search_parameter = search_parameter;
    }
}

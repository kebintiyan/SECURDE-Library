package com.securde.model.account;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by kevin on 6/25/2017.
 */

@Table(name = "temp_admins")
public class TempAdmin extends Admin{

    private String dateTimeCreated;

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public TempAdmin setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
        return this;
    }
}

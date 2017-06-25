package com.securde.model.account;

import javax.persistence.*;

/**
 * Created by kevin on 6/25/2017.
 */

@Entity
@Table(name = "admins")
public class Admin {

    public enum Type {
        MANAGER,
        STAFF,
        ADMINISTRATOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Type adminType;

    public Admin() {}

    public Integer getAdminId() {
        return adminId;
    }

    public Admin setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Admin setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    public Type getAdminType() {
        return adminType;
    }

    public Admin setAdminType(Type adminType) {
        this.adminType = adminType;
        return this;
    }
}

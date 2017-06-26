package com.securde.model.account;

import javax.persistence.*;

/**
 * Created by kevin on 6/21/2017.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String idNumber;
    private String email;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String birthday;
    private String secretQuestion;
    private String secretAnswer;

    private String dateTimeCreated;

    public User() {

    }

    public Integer getUserId() {
        return userId;
    }

    public User setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public User setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public User setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public User setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public User setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
        return this;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public User setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
        return this;
    }

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public User setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated;
        return this;
    }
}

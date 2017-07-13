package com.securde.model.account;

import org.hibernate.validator.constraints.*;

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

    @SafeHtml
    private String username;

    @SafeHtml
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @SafeHtml
    private String idNumber;

    @SafeHtml
    private String email;

    @SafeHtml
    private String firstName;

    @SafeHtml
    private String middleInitial;

    @SafeHtml
    private String lastName;

    @SafeHtml
    private String birthday;

    @SafeHtml
    private String secretQuestion;

    @SafeHtml
    private String secretAnswer;

    @Column(name = "temp")
    private Boolean temp;

    @Column(name = "active")
    private Boolean active;

    private String dateTimeUpdated;

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
        this.username = username.trim();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password.trim();
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
        this.idNumber = idNumber.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email.trim();
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName.trim();
        return this;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public User setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial.trim();
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName.trim();
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public User setBirthday(String birthday) {
        this.birthday = birthday.trim();
        return this;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public User setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion.trim();
        return this;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public User setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer.trim();
        return this;
    }

    public Boolean isTemp() {
        return temp;
    }

    public User setTemp(Boolean temp) {
        this.temp = temp;
        return this;
    }

    public Boolean isActive() {
        return active;
    }

    public User setActive(Boolean active) {
        this.active = active;
        return this;
    }

    public String getDateTimeUpdated() {
        return dateTimeUpdated;
    }

    public User setDateTimeUpdated(String dateTimeUpdated) {
        this.dateTimeUpdated = dateTimeUpdated.trim();
        return this;
    }

    public boolean isRegularUser() {
        return role == Role.STUDENT || role == Role.FACULTY;
    }
}

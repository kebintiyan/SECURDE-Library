package com.securde.model.account;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
//    @NotNull(message = "Please provide a valid username")
//    @Size(min = 6, max = 16, message = "Username must have at least 6 characters and cannot exceed " +
//            "16 characters.")
    private String username;

    @SafeHtml
/*    @NotNull(message = "Please provide a valid password")
    @Size(min = 6, max = 16, message = "Password must have at least 6 characters and cannot exceed " +
            "16 characters.")*/
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @SafeHtml
//    @NotNull
//    @Size(min = 8, max = 8, message = "Please input a valid ID number.")
    private String idNumber;

    @SafeHtml
//    @NotNull(message = "Please input a valid email.")
//    @NotBlank(message = "Please input a valid email.")
    private String email;

    @SafeHtml
//    @NotNull(message = "Please input your first name.")
/*    @NotBlank(message = "Please input your first name.")
    @Size(max = 64, message = "First name is too long.")*/
    private String firstName;

    @SafeHtml
//    @NotNull(message = "Please input your middle initial.")
    /*@NotBlank(message = "Please input your middle initial.")
    @Size(max = 5, message = "Middle initial is too long.")*/
    private String middleInitial;

    @SafeHtml
//    @NotNull(message = "Please input your last name.")
    /*@NotBlank(message = "Please input your last name.")
    @Size(max = 64, message = "Last name is too long.")*/
    private String lastName;

    @SafeHtml
//    @NotNull(message = "Please input your birthday.")
//    @NotBlank(message = "Please input your birthday.")
    private String birthday;

    @SafeHtml
//    @NotNull(message = "Please input a secret question.")
    /*@NotBlank(message = "Please input a secret question.")
    @Size(max = 128, message = "Secret question is too long.")*/
    private String secretQuestion;

    @SafeHtml
//    @NotNull(message = "Please input a secret answer.")
    /*@NotBlank(message = "Please input a secret answer.")
    @Size(max = 32, message = "Secret answer is too long.")*/
    private String secretAnswer;

    @Column(name = "temp")
    private boolean temp;

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

    public boolean isTemp() {
        return temp;
    }

    public User setTemp(boolean temp) {
        this.temp = temp;
        return this;
    }

    public String getDateTimeCreated() {
        return dateTimeCreated;
    }

    public User setDateTimeCreated(String dateTimeCreated) {
        this.dateTimeCreated = dateTimeCreated.trim();
        return this;
    }

    public boolean isRegularUser() {
        return role == Role.STUDENT || role == Role.FACULTY;
    }
}

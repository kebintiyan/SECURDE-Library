package com.securde.service;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kevin on 6/25/2017.
 */

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByIdNumber(String idNumber) {
        return userRepository.findByIdNumber(idNumber);
    }

    public User findUserByUserId(Integer id) {
        return userRepository.findOne(id);
    }

    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSecretAnswer(passwordEncoder.encode(user.getSecretAnswer()));
        user.setActive(true);
        user.setTemp(false);

        if (user.getIdNumber() != null) {
            if (user.getIdNumber().charAt(0) == '2') {
                user.setRole(Role.FACULTY);
            }
            else {
                user.setRole(Role.STUDENT);
            }
        }

        userRepository.save(user);
    }

    public void createNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setTemp(true);

        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public ArrayList<User> getInactiveUsers() {
        return userRepository.findByActive(false);
    }

    public void lockTemporaryAccounts() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofDays(1));
        Date dateBefore = Date.from(before);

//        System.out.println(dateFormat.format(dateBefore));

        userRepository.lockTemporaryAccounts(dateFormat.format(dateBefore));
    }

    public boolean validateUser(String username, String password) {
        User user = findUserByUsername(username);

        if (user == null)
            return false;

        return passwordEncoder.matches(password, user.getPassword());
    }

    public void changePassword(String username, String newPassword) {
        User user = findUserByUsername(username);

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setTemp(false);

        userRepository.save(user);
    }
}

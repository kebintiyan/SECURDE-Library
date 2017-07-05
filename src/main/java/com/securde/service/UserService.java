package com.securde.service;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getSecretAnswer() != null) {
            user.setSecretAnswer(passwordEncoder.encode(user.getSecretAnswer()));
        }

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

    public ArrayList<User> getInactiveUsers() {
        return userRepository.findByActive(false);
    }
}

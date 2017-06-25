package com.securde.service;

import com.securde.model.account.User;

/**
 * Created by kevin on 6/25/2017.
 */
public interface UserServiceInterface {

    public User findByUsername(String username);
    public User findByEmail(String email);
    public void save(User user);
}

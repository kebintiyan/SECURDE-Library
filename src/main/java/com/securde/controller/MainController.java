package com.securde.controller;

import com.securde.model.account.User;
import com.securde.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 6/21/2017.
 */

@RestController
public class MainController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("")
    public void hello() {
        User user = new User()
                .setUserId("11425520")
                .setFirstName("Kevin")
                .setMiddleInitial("D.")
                .setLastName("Chan")
                .setUsername("kebintiyan")
                .setPassword("password")
                .setEmailAddress("kevin98gray@gmail.com")
                .setBirthday("1998-01-11")
                .setSecretQuestion("Test")
                .setSecretAnswer("Test")
                .setUserType(User.Type.STUDENT);

        userRepository.save(user);
    }

    @RequestMapping("/admin/test")
    public void admin() {
        System.out.println("admin");
    }
}

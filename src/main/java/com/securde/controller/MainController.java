package com.securde.controller;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 6/21/2017.
 */

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping("")
    public void hello() {
        /*User user = new User()
                .setIdNumber("11425520")
                .setFirstName("Kevin")
                .setMiddleInitial("D.")
                .setLastName("Chan")
                .setUsername("kebintiyan")
                .setPassword("password")
                .setEmail("kevin98gray@gmail.com")
                .setBirthday("1998-01-11")
                .setSecretQuestion("Test")
                .setSecretAnswer("Test")
                .setRole(Role.STUDENT);


        userService.saveUser(user);*/
    }
}

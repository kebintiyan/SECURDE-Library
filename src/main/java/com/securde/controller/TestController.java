package com.securde.controller;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kevin on 6/26/2017.
 */

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(value={"/test"}, method = RequestMethod.GET)
    public void test(){
        /*User user = new User()
                .setIdNumber("11425520")
                .setUsername("user")
                .setPassword("password")
                .setEmail("test@test.com")
                .setRole(Role.STUDENT)
                .setFirstName("Test")
                .setMiddleInitial("T.")
                .setLastName("Test")
                .setBirthday("1998-01-11")
                .setSecretQuestion("The answer is answer")
                .setSecretAnswer("answer");

        userService.saveUser(user);

        System.out.println(userService.findUserByUsername("user").getPassword());*/
    }
}

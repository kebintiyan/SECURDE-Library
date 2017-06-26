package com.securde.controller;

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

    /*@RequestMapping("/")
    public void test() {
        User user = userService.findUserByUsername("admin");

        System.out.println(user.isTemp());
    }*/

    @RequestMapping(value={"/login"}, method = RequestMethod.POST)
    public void login(){
        System.out.println("Heeeeere");
    }
}

package com.securde.controller;

import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by kevin on 6/26/2017.
 */

@RestController
public class TestController {

    @Autowired
    ReservableService reservableService;

    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public void test() {
        System.out.println(userService.findUserByUsername("test").isActive());
    }
}

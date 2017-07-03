package com.securde.controller;

import com.securde.service.ReservationService;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by patricktobias on 02/07/2017.
 */
@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/text/reserve", method = RequestMethod.POST)
    public ModelAndView reserveText (/*@RequestParam("id") Integer id*/Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) authentication.getPrincipal();
        System.out.println(userService.findUserByUsername(user.getUsername()).getRole());

        modelAndView.setViewName("reserve");

        return modelAndView;
    }

    @RequestMapping(value = "/rooms/reserve", method = RequestMethod.POST)
    public ModelAndView reserveRoom (/*@RequestParam("id") Integer id*/) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("reserve");

        return modelAndView;
    }

}

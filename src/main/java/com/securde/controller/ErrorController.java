package com.securde.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping(value = {"/invalid_session"}, method = RequestMethod.GET)
    public ModelAndView viewInvalidSession() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/error");
        modelAndView.addObject("errorMessage",
                "Your session is invalid.");
        return modelAndView;
    }

    @RequestMapping(value = {"/expired_session"}, method = RequestMethod.GET)
    public ModelAndView viewExpiredSession() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/error");
        modelAndView.addObject("errorMessage",
                "Your session has expired.");
        return modelAndView;
    }

    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    public ModelAndView viewError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/error");
        modelAndView.addObject("errorMessage",
                "You're seeing this page because something wrong happened. Please try again.");
        return modelAndView;
    }
}

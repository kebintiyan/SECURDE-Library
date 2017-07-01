package com.securde.controller;

import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by avggo on 7/1/2017.
 */

@Controller
public class SearchController {
    @Autowired
    ReservableService reservableService;


    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }
}

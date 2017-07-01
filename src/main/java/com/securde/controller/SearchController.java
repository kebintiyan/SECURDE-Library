package com.securde.controller;

import com.securde.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by avggo on 7/1/2017.
 */

@Controller
public class SearchController {
    @Autowired
    TextService textService;

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        String text = new String();
        modelAndView.addObject("text", text);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView retrieveSearch(String text) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        System.out.print(text);

        return modelAndView;
    }
}

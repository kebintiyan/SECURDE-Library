package com.securde.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class AdminViewController {

    @RequestMapping(value = {"/admin/home"}, method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}

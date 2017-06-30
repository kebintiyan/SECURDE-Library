package com.securde.controller.admin;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class AdminController {

    @RequestMapping(value = {"/admin/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/create"}, method = RequestMethod.GET)
    public ModelAndView viewCreate() {
        ModelAndView modelAndView = new ModelAndView();

        User user = new User();
        modelAndView.addObject("user", user);

        String[] roles = {"Library Manager", "Library Staff"};
        modelAndView.addObject("roles", roles);

        modelAndView.setViewName("admin/create");

        return modelAndView;
    }
}

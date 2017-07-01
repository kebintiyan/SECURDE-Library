package com.securde.controller.manager;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.account.UserValidator;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class ManagerController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/manager", "/manager/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/home");
        return modelAndView;
    }


}

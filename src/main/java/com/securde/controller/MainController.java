package com.securde.controller;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.account.UserValidator;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by kevin on 6/21/2017.
 */

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = {"/"})
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findUserByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already in use.");
        }

        if (userService.findUserByIdNumber(user.getIdNumber()) != null) {
            bindingResult.rejectValue("idNumber", "error.user", "ID Number is already in use.");
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user","Email is already in use.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        }
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }
}

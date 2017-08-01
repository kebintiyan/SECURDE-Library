package com.securde.controller;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.reservable.SearchParameters;
import com.securde.service.LoginAttemptService;
import com.securde.validator.UserValidator;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by kevin on 6/21/2017.
 */

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    LoginAttemptService loginAttemptService;

    @Autowired
    UserValidator userValidator;

    /*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }*/

    @RequestMapping("/home")
    public RedirectView redirectAfterLogin(Authentication auth) {
        RedirectView redirectView = new RedirectView();

        String role = auth.getAuthorities().toArray()[0].toString();

        if (role.equals(Role.ADMINISTRATOR.toString())) {
            redirectView.setUrl("/admin/home");
        }
        else if (role.equals(Role.MANAGER.toString())) {
            redirectView.setUrl("/manager/home");
        }
        else if (role.equals(Role.STAFF.toString())) {
            redirectView.setUrl("/staff/home");
        }
        else {
            redirectView.setUrl("/user/home");
        }

        return redirectView;
    }

    @RequestMapping(value = {"/user","/user/home"}, method = RequestMethod.GET)
    public ModelAndView home () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        return modelAndView;
    }

    /*@RequestMapping(value = {"/"})
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        return modelAndView;
    }*/

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public @ResponseBody ModelAndView login(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String ip = request.getRemoteAddr();

        if (loginAttemptService.isBlocked(ip)) {
            modelAndView.setViewName("blocked");

            System.out.println(ip + " is blocked.");

        } else {
            modelAndView.setViewName("login2");

        }

        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register2");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(user, bindingResult);

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
            modelAndView.setViewName("register2");
        }
        else {
            userService.createNewUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register2");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public ModelAndView viewAccessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access-denied");
        return modelAndView;
    }
}

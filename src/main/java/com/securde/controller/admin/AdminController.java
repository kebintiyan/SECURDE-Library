package com.securde.controller.admin;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.account.UserValidator;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = {"/admin", "/admin/home"}, method = RequestMethod.GET)
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

    @RequestMapping(value = {"/admin/create"}, method = RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, @RequestParam("radio_role") String role, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(role.equals("manager"))
            user.setRole(Role.MANAGER);
        else
            user.setRole(Role.STAFF);

        if (userService.findUserByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Username is already in use.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/admin/create");
        }
        else {
            userService.createNewAdmin(user);
            /*modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());*/
            modelAndView.addObject("registered", true);
            modelAndView.setViewName("redirect:/admin/home");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/admin/unlock"}, method = RequestMethod.GET)
    public ModelAndView viewUnlockAccount() {
        ModelAndView modelAndView = new ModelAndView();

        ArrayList<User> users = userService.getInactiveUsers();
        modelAndView.addObject("users", users);

        modelAndView.setViewName("admin/unlock_account");

        return modelAndView;
    }

    @RequestMapping(value = {"/admin/unlock"}, method = RequestMethod.PUT)
    public ModelAndView unlockAccount(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserByUserId(id);
        user.setActive(true);

        userService.updateUser(user);

        modelAndView.setViewName("redirect:/admin/unlock");
        return modelAndView;
    }
}

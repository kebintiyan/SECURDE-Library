package com.securde.controller.user;

import com.securde.model.account.User;
import com.securde.service.UserService;
import com.securde.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
    public ModelAndView viewForgotPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/forgot_password");
        return modelAndView;
    }

    @RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
    public ModelAndView submitUsername(@RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByUsername(username);

        if (user == null) {
            modelAndView.setViewName("user/forgot_password");
            modelAndView.addObject("errorMessage", "User not found.");
        }
        else {
            modelAndView.setViewName("user/secret_question");
            modelAndView.addObject("userId", user.getUserId());
            modelAndView.addObject("secretQuestion", user.getSecretQuestion());
        }

        return modelAndView;
    }

    @RequestMapping(value = "/secret_question", method = RequestMethod.POST)
    public ModelAndView submitAnswer(@RequestParam("userId") Integer userId, @RequestParam("secretAnswer") String secretAnswer) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByUserId(userId);

        if (user == null) {
            modelAndView.setViewName("user/forgot_password");
            modelAndView.addObject("errorMessage", "User not found.");
        }
        else {
            if (userService.validateSecretAnswer(user.getUserId(), secretAnswer)) {
                modelAndView.setViewName("user/change_password");
                modelAndView.addObject("userId", user.getUserId());
            }
            else {
                modelAndView.setViewName("user/secret_question");
                modelAndView.addObject("errorMessage", "Incorrect answer");
                modelAndView.addObject("userId", user.getUserId());
                modelAndView.addObject("secretQuestion", user.getSecretQuestion());
            }

        }

        return modelAndView;
    }

    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam("userId") Integer userId, @RequestParam("newPassword") String password,
                                       @RequestParam("confirmNewPassword") String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByUserId(userId);

        if (user == null) {
            modelAndView.setViewName("user/forgot_password");
            modelAndView.addObject("errorMessage", "User not found.");
        }
        else {
            // Validate password
            PasswordValidator passwordValidator = new PasswordValidator();
            boolean hasError;

            hasError = !password.equals(confirmPassword);
            hasError = hasError || !passwordValidator.isValidPassword(password);

            if (hasError) {
                modelAndView.setViewName("user/change_password");
                modelAndView.addObject("errorMessage", "Invalid password.");
                modelAndView.addObject("userId", user.getUserId());

            }
            else {
                userService.changePassword(user.getUsername(), password);
                modelAndView.setViewName("redirect:/login");
            }

        }

        return modelAndView;
    }
}

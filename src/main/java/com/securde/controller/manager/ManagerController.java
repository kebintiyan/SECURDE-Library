package com.securde.controller.manager;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.account.UserValidator;
import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
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

    @Autowired
    ReservableService reservableService;

    @RequestMapping(value = {"/manager", "/manager/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/home");
        return modelAndView;
    }

    @RequestMapping(value = {"/manager/text_management"}, method = RequestMethod.GET)
    public ModelAndView viewManageTexts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("texts", reservableService.getAllTexts());
        modelAndView.setViewName("manager/text_management");
        return modelAndView;
    }

    @RequestMapping(value = {"/manager/text/details"}, method = RequestMethod.GET)
    public ModelAndView viewTextDetails(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Text text = reservableService.getText(id);

        modelAndView.addObject("text", text);
        modelAndView.setViewName("manager/text_details");

        return modelAndView;
    }

}

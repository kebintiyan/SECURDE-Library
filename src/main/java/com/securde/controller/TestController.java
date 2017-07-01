package com.securde.controller;

import com.securde.model.account.Role;
import com.securde.model.account.User;
import com.securde.model.reservable.Text;
import com.securde.service.TextService;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by kevin on 6/26/2017.
 */

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    TextService textService;

}

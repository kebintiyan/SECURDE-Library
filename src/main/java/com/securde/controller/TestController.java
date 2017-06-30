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

<<<<<<< HEAD
    /*@RequestMapping("/")
    public void test() {
        User user = userService.findUserByUsername("admin");

        userService.saveUser(user);*/

    /*@RequestMapping("/")
    public void test() {
        ArrayList<Text> text = textService.findTextByTitleContaining("mis");

        for(int i = 0; i < text.size(); i++)
            System.out.println(text.get(i).getTitle());

        //System.out.println(text.getTitle());
    }*/

    /*@RequestMapping(value={"/login"}, method = RequestMethod.POST)
    public void login(){
        System.out.println("Heeeeere");
    }*/
=======
>>>>>>> 71aff4394f3c595e9b46af0c914547f38b79a9a5
}

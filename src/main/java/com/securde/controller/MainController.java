package com.securde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kevin on 6/21/2017.
 */

@Controller
public class MainController {

    @RequestMapping("")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/admin/test")
    public void admin() {
        System.out.println("admin");
    }
}

package com.securde.controller;

import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by kevin on 6/26/2017.
 */

@RestController
public class TestController {

    @Autowired
    ReservableService reservableService;

    @RequestMapping("/test")
    public void test() {

    }
}

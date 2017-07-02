package com.securde.controller;

import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by patricktobias on 02/07/2017.
 */
@Controller
public class TextController {

    @Autowired
    ReservableService reservableService;

    @RequestMapping(value="/text", method = RequestMethod.GET)
    public ModelAndView home (@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Text text = reservableService.getText(id);

        modelAndView.setViewName("text");
        modelAndView.addObject(text);

        return modelAndView;
    }

}

package com.securde.controller;

import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by patricktobias on 02/07/2017.
 */
@Controller
public class TextController {

    @Autowired
    ReservableService reservableService;

    private static List<String> getDates() {
        Calendar calendar = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date dateToday = new Date();

        calendar.setTime(dateToday);

        List<String> dates = new ArrayList<String>();

        int date_inc = 0;
        for (int i = 0; i < dates.size(); i++) {
            calendar.add(Calendar.DATE, date_inc++);

            dates.add(sdf.format(calendar.getTime()));
        }

        return dates;
    }

    @RequestMapping(value="/text", method = RequestMethod.GET)
    public ModelAndView home (@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Text text = reservableService.getText(id);

        List<String> availableDates = getDates();

        modelAndView.setViewName("text");
        modelAndView.addObject(text);
        modelAndView.addObject("dates", availableDates);

        return modelAndView;
    }

}

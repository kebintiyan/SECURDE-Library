package com.securde.controller;

import com.securde.model.reservable.Text;
import com.securde.model.reservation.TextReservation;
import com.securde.service.ReservableService;
import com.securde.service.ReservationService;
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

    @Autowired
    ReservationService reservationService;

    // 'int daysAfter' is the days going to be included after the date today
    private static List<String> getDates(int daysAfter) { // Today's date is included
        Calendar calendar = Calendar.getInstance();
        DateFormat value_sdf = new SimpleDateFormat("yyyy-MM-dd");
        //DateFormat display_sdf = new SimpleDateFormat("MMMM-dd-yyyy");
        Date dateToday = new Date();

        calendar.setTime(dateToday);

        List<String> dates = new ArrayList<String>();

        dates.add(value_sdf.format(calendar.getTime())); // add today
        for (int i = 0; i < daysAfter; i++) {
            calendar.add(Calendar.DATE, 1);

            dates.add(value_sdf.format(calendar.getTime()));
        }

        return dates;
    }

    @RequestMapping(value="/text", method = RequestMethod.GET)
    public ModelAndView home (@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Text text = reservableService.getText(id);

        TextReservation textReservation = new TextReservation();

        //List<String> availableDates = getDates(7);

        modelAndView.setViewName("text");
        modelAndView.addObject("reservation", textReservation);
        modelAndView.addObject(text);
        //modelAndView.addObject("dates", availableDates);

        return modelAndView;
    }

}

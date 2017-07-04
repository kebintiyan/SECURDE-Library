package com.securde.controller;

import com.securde.model.account.Role;
import com.securde.model.reservable.Text;
import com.securde.model.reservation.TextReservation;
import com.securde.service.ReservableService;
import com.securde.service.ReservationService;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by patricktobias on 02/07/2017.
 */
@Controller
public class ReservationController {

    public static final int STUDENT_RESERVATION_DAYS = 7;
    public static final int FACULTY_RESERVATION_DAYS = 30;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    ReservableService reservableService;

    @RequestMapping(value = "/text/reserve", method = RequestMethod.POST)
    public ModelAndView reserveText (@RequestParam("id") String id, TextReservation textReservation, Authentication authentication) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) authentication.getPrincipal();
        com.securde.model.account.User my_user = userService.findUserByUsername(user.getUsername());

        textReservation.setUser(my_user);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd");
        Date date = sdf.parse(textReservation.getReservationStartDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (my_user.getRole().equals(Role.STUDENT)) {
            calendar.add(Calendar.DATE, STUDENT_RESERVATION_DAYS);
        } else if (my_user.getRole().equals(Role.FACULTY)) {
            calendar.add(Calendar.DATE, FACULTY_RESERVATION_DAYS);
        }

        String endDate = sdf.format(calendar.getTime());
        textReservation.setReservationEndDate(endDate);

        Text text = reservableService.getText(Integer.parseInt(id));

        textReservation.setText(text);

        reservationService.saveTextReservation(textReservation);

        modelAndView.setViewName("reserve");

        modelAndView.addObject(textReservation);
        modelAndView.addObject(text);

        return modelAndView;
    }

    @RequestMapping(value = "/rooms/reserve", method = RequestMethod.POST)
    public @ResponseBody ModelAndView reserveRoom (@RequestParam("chosenSlot") String chosenSlotString) {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("RESERVE ROOM");

        modelAndView.setViewName("reserve");

        return modelAndView;
    }

}

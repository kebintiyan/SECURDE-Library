package com.securde.controller;

import com.securde.model.reservable.Room;
import com.securde.model.reservation.RoomReservation;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patricktobias on 02/07/2017.
 */

@Controller
public class RoomController {

    @Autowired
    ReservableService reservableService;

    private static List<String> getTimes () {
        int startTime = 7;

        List<String> times = new ArrayList<>();

        while (startTime < 20) {
            String time = startTime + ":00-";

            startTime++;

            time += startTime + ":00";

            times.add(time);
        }

        return times;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public ModelAndView home () {
        ModelAndView modelAndView = new ModelAndView();

        RoomReservation roomReservation = new RoomReservation();

        List<Room> rooms = reservableService.getAllRooms();

        modelAndView.setViewName("rooms");
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("times", getTimes());
        modelAndView.addObject(roomReservation);

        return modelAndView;
    }

}

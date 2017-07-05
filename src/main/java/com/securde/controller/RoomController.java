package com.securde.controller;

import com.securde.model.reservable.Room;
import com.securde.model.reservation.RoomReservation;
import com.securde.service.ReservableService;
import com.securde.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by patricktobias on 02/07/2017.
 */

@Controller
public class RoomController {

    @Autowired
    ReservableService reservableService;

    @Autowired
    ReservationService reservationService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar calendar = Calendar.getInstance();

    public static List<List<String>> getTimes() {
        int startTime = 7;

        List<List<String>> times = new ArrayList<List<String>>();

        while (startTime < 20) {
            List<String> timerange = new ArrayList<>();

            String time = startTime + ":00";

            timerange.add(time);

            startTime++;

            time = startTime + ":00";

            timerange.add(time);

            times.add(timerange);
        }

        return times;
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public ModelAndView home (@RequestParam(value = "date", required = false) String inputDate) {
        ModelAndView modelAndView = new ModelAndView();

        List<Room> rooms = reservableService.getAllRooms();

        if (inputDate == null) {
            Date date = new Date();
            inputDate = sdf.format(date);
        }

        ArrayList<RoomReservation> reservedSlots = reservationService.getRoomReservationsByDate(inputDate);

        List<RoomReservation.RoomIDAndStartTime> roomIDAndStartTimes = new ArrayList<>();

        for (int i = 0; i < reservedSlots.size(); i++) {
            RoomReservation reservedSlot = reservedSlots.get(i);
            roomIDAndStartTimes.add(new RoomReservation.RoomIDAndStartTime(reservedSlot.getRoom().getRoomId(), reservedSlot.getReservationStartTime()));
        }

        modelAndView.setViewName("rooms");
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("times", getTimes());
        modelAndView.addObject("reserved_slots", roomIDAndStartTimes);
        modelAndView.addObject("inputDate", inputDate);

        return modelAndView;
    }

}

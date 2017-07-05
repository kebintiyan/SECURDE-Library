package com.securde.controller;

import com.securde.model.reservable.Room;
import com.securde.model.reservation.RoomReservation;
import com.securde.service.ReservableService;
import com.securde.service.ReservationService;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    UserService userService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar calendar = Calendar.getInstance();

    private static List<List<String>> getTimes () {
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
    public ModelAndView viewReserveRoom (@RequestParam(value = "date", required = false) String inputDate,
                                         Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();

        List<Room> rooms = reservableService.getAllRooms();

        if (inputDate == null) {
            Date date = new Date();
            inputDate = sdf.format(date);
        }

        ArrayList<RoomReservation> reservedSlots = reservationService.getRoomReservationsByDate(inputDate);

        List<RoomIDAndStartTime> roomIDAndStartTimes = new ArrayList<>();

        for (int i = 0; i < reservedSlots.size(); i++) {
            RoomReservation reservedSlot = reservedSlots.get(i);
            roomIDAndStartTimes.add(new RoomIDAndStartTime(reservedSlot.getRoom().getRoomId(), reservedSlot.getReservationStartTime()));
        }

        User authUser = (User) authentication.getPrincipal();
        com.securde.model.account.User user = userService.findUserByUsername(authUser.getUsername());

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Boolean hasReservation = reservationService.getRoomReservationsByUserFromDate(user.getUserId(), sdf.format(date)).size() > 0;

        modelAndView.setViewName("rooms");
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("times", getTimes());
        modelAndView.addObject("reserved_slots", roomIDAndStartTimes);
        modelAndView.addObject("inputDate", inputDate);
        modelAndView.addObject("hasReservation", hasReservation);

        return modelAndView;
    }

    public class RoomIDAndStartTime {

        Integer id;
        String time;

        public RoomIDAndStartTime () {

        }

        public RoomIDAndStartTime (Integer id, String time) {
            this.id = id;
            this.time = time;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}

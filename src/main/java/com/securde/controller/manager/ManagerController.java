package com.securde.controller.manager;

import com.securde.controller.RoomController;
import com.securde.model.reservable.Room;
import com.securde.model.reservable.Text;
import com.securde.model.reservation.RoomReservation;
import com.securde.service.ReservableService;
import com.securde.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class ManagerController {

    @Autowired
    ReservableService reservableService;

    @Autowired
    ReservationService reservationService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Calendar calendar = Calendar.getInstance();

    @RequestMapping(value = {"/manager", "/manager/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/home");
        return modelAndView;
    }

    @RequestMapping(value = {"/manager/text"}, params = "!id", method = RequestMethod.GET)
    public ModelAndView viewManageTexts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("texts", reservableService.getAllTexts());
        modelAndView.setViewName("manager/text_management");
        return modelAndView;
    }

    @RequestMapping(value = {"/manager/text"}, method = RequestMethod.GET)
    public ModelAndView viewTextDetails(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        Text text = reservableService.getText(id);

        modelAndView.addObject("text", text);
        modelAndView.setViewName("manager/text_details");

        return modelAndView;
    }

    @RequestMapping(value = {"/manager/text/add"}, method = RequestMethod.GET)
    public ModelAndView viewAddText() {
        ModelAndView modelAndView = new ModelAndView();

        ArrayList<String> tags = new ArrayList<>();

        while(tags.size() < 5) {
            tags.add("");
        }

        Text text = new Text()
                .setTags(tags);

        modelAndView.addObject("text", text);
        modelAndView.setViewName("manager/add_text");

        return modelAndView;
    }

    @RequestMapping(value = {"/manager/text/add"}, method = RequestMethod.POST)
    public ModelAndView addText(@Valid Text text, BindingResult bindingResult, @RequestParam("radio-type") String type) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("manager/add_text");
        }
        else {
            text.setType(type).setStatus(Text.Status.AVAILABLE);
            reservableService.saveText(text);
            modelAndView.setViewName("redirect:/manager/text");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"manager/text"}, method = RequestMethod.PUT)
    public ModelAndView editTextDetails(@Valid Text text, BindingResult bindingResult, @RequestParam("radio-type") String type) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("error", true);
            modelAndView.setViewName("manager/text_details");
        }
        else {
            text.setType(type);
            reservableService.saveText(text);
            modelAndView.setViewName("redirect:/manager/text?id=" + text.getTextId());
        }

        return modelAndView;
    }

    @RequestMapping(value = {"manager/text"}, method = RequestMethod.DELETE)
    public RedirectView deleteText(@RequestParam("text-id") Integer id) {
        reservableService.deleteText(id);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/manager/text");

        return redirectView;
    }

    @RequestMapping(value = {"manager/rooms"}, method = RequestMethod.GET)
    public ModelAndView viewRooms(@RequestParam(value = "date", required = false) String inputDate) {
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

        modelAndView.setViewName("manager/rooms");
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("times", RoomController.getTimes());
        modelAndView.addObject("reserved_slots", roomIDAndStartTimes);
        modelAndView.addObject("inputDate", inputDate);

        return modelAndView;
    }
}

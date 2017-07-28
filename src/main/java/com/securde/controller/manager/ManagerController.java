package com.securde.controller.manager;

import com.securde.controller.RoomController;
import com.securde.export.XlsxView;
import com.securde.model.reservable.Room;
import com.securde.model.reservable.Text;
import com.securde.model.reservation.RoomReservation;
import com.securde.service.ReservableService;
import com.securde.service.ReservationService;
import com.securde.service.UserService;
import com.securde.validator.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class ManagerController {

    @Autowired
    ReservableService reservableService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

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

    @RequestMapping(value = "manager/rooms/delete", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView deleteRoom (@RequestParam("msg") String msg, @RequestParam("date") String date) {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println(msg);

        String[] splitMessage = msg.split("-");

        Integer roomId = Integer.parseInt(splitMessage[0]);
        String startTime = splitMessage[1];

        ArrayList<RoomReservation> retrievedSlots = reservationService.getRoomReservationByRoomIdFromStartTimeAndDate(roomId, startTime, date);

        for (int i = 0; i < retrievedSlots.size(); i++){
            reservationService.deleteRoomReservation(retrievedSlots.get(i).getRoomReservationId());
        }

        modelAndView.setViewName("manager/delete");

        return modelAndView;
    }

    @RequestMapping(value = {"manager/change_password"}, method = RequestMethod.GET)
    public ModelAndView viewChangePassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("manager/change_password");
        return modelAndView;
    }

    @RequestMapping(value = {"manager/change_password"}, method = RequestMethod.POST)
    public ModelAndView changePassword(Authentication authentication,
                                       @RequestParam("currentPassword") String currentPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       @RequestParam("confirmNewPassword") String confirmNewPassword) {

        ModelAndView modelAndView = new ModelAndView();

        User authUser = (User) authentication.getPrincipal();

        boolean hasError;

        PasswordValidator passwordValidator = new PasswordValidator();

        hasError = !userService.validateUser(authUser.getUsername(), currentPassword);
        hasError = hasError || !newPassword.equals(confirmNewPassword);
        hasError = hasError || !passwordValidator.isValidPassword(currentPassword) ||
                !passwordValidator.isValidPassword(newPassword);

        if (hasError) {
            modelAndView.addObject("errorMessage", "Invalid input. Try again.");
        }
        else {
            // Save changes
            userService.changePassword(authUser.getUsername(), newPassword);
            modelAndView.addObject("successMessage", "Successfully changed password.");
        }

        modelAndView.setViewName("manager/change_password");

        return modelAndView;
    }

    @RequestMapping(value = {"manager/status"}, method = RequestMethod.GET)
    public ModelAndView getLogs() {
        HashMap<String, Object> model = new HashMap<>();

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);

        model.put("textReservations", reservationService.getTextReservationsFromDate(currentDate));
        model.put("roomReservations", reservationService.getRoomReservationsFromDate(currentDate));

        return new ModelAndView(new XlsxView(), model);
    }
}

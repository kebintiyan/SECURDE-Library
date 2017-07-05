package com.securde.controller;

import com.securde.model.reservable.Review;
import com.securde.model.reservable.Text;
import com.securde.model.reservation.TextReservation;
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

import java.text.DateFormat;
import java.text.ParseException;
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

    @Autowired
    UserService userService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd");
    private Calendar calendar = Calendar.getInstance();

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
    public ModelAndView viewText (@RequestParam("id") Integer id, Authentication authentication) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();

        Text text = reservableService.getText(id);

        User authUser = (User) authentication.getPrincipal();
        com.securde.model.account.User user = userService.findUserByUsername(authUser.getUsername());

        Integer reservationCount = reservationService.getPreviousReservationsByUserIdAndTextId(user.getUserId(), id).size();

        TextReservation textReservation = new TextReservation();
        Review review = new Review()
                .setUser(user)
                .setText(text);

        ArrayList<Review> reviews = reservableService.getReviewsByTextId(id);

        ArrayList<TextReservation> reservations = reservationService.findTextReservationByTextId(id);
        ArrayList<String> reservationStrings = new ArrayList<>();

        for(int i = 0; i < reservations.size(); i++){
            Date curStartDate = sdf.parse(reservations.get(i).getReservationStartDate());
            Date curEndDate = sdf.parse(reservations.get(i).getReservationEndDate());

            calendar.setTime(curStartDate);

            while(calendar.getTimeInMillis() <= curEndDate.getTime()) {
                reservationStrings.add(sdf.format(calendar.getTime()));
                calendar.add(Calendar.DATE, 1);
            }
        }

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Boolean hasReservation = reservationService.getReservationsByUserIdAndTextIdFromDate(user.getUserId(), id, sdf.format(date)).size() > 0;
        //List<String> availableDates = getDates(7);

        modelAndView.setViewName("text");
        modelAndView.addObject("reservation", textReservation);
        modelAndView.addObject("text", text);
        modelAndView.addObject("reservationCount", reservationCount);
        modelAndView.addObject("review", review);
        modelAndView.addObject("reviews", reviews);
        modelAndView.addObject("reservationStrings", reservationStrings);
        modelAndView.addObject("hasReservation", hasReservation);

        //modelAndView.addObject("dates", availableDates);

        return modelAndView;
    }

    @RequestMapping(value = {"/text/review"}, method = RequestMethod.POST)
    public ModelAndView writeReview(Review review) {
        ModelAndView modelAndView = new ModelAndView();

        reservableService.saveReview(review);

        modelAndView.setViewName("redirect:/text?id=" + review.getText().getTextId());
        return modelAndView;
    }
}

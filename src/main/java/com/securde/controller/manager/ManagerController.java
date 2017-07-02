package com.securde.controller.manager;

import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by kevin on 6/26/2017.
 */

@Controller
public class ManagerController {

    @Autowired
    ReservableService reservableService;

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
    public RedirectView addText(Text text, @RequestParam("radio-type") String type) {

        text.setType(type).setStatus(Text.Status.AVAILABLE);
        reservableService.saveText(text);

        // Invalid
        /*if (false) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("text", text);
            modelAndView.setViewName("manager/add_text");

            return modelAndView;
        }*/

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/manager/text");
        return redirectView;
    }

    @RequestMapping(value = {"manager/text"}, method = RequestMethod.PUT)
    public RedirectView editTextDetails(Text text, @RequestParam("radio-type") String type) {
        // Validate

        text.setType(type);
        reservableService.saveText(text);

        // Redirect
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/manager/text?id=" + text.getTextId());


        return redirectView;
    }
}

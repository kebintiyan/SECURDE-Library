package com.securde.controller;

import com.securde.model.reservable.SearchParameters;
import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avggo on 7/1/2017.
 */

@Controller
public class SearchController {
    @Autowired
    ReservableService reservableService;

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("search_parameters", searchParameters);

        return modelAndView;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView retrieveSearch(SearchParameters searchParameters, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> searchResult = reservableService.searchText(searchParameters.getSearch_parameter());

        if(searchResult.size() > 0)
            for(int i = 0; i < searchResult.size(); i++)
                System.out.println(reservableService.searchText(searchParameters.getSearch_parameter()).get(0).getTitle());

        return modelAndView;
    }
}

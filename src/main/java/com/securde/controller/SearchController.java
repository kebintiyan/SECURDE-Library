package com.securde.controller;

import com.securde.model.reservable.SearchParameters;
import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

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
        modelAndView.addObject("searchParameters", searchParameters);

        return modelAndView;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView retrieveSearch(SearchParameters searchParameters, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> searchResult;

        System.out.println("Author: " + searchParameters.getAuthor() + "");
        System.out.println("Title: " + searchParameters.getTitle() + "");
        System.out.println("Publisher: " + searchParameters.getPublisher() + "");

        if(searchParameters.getSearchString().length() > 0){

            String searchString = searchParameters.getSearchString();

            if(searchParameters.getAuthor() == true){
                if(searchParameters.getTitle() == true)
                    searchResult = reservableService.findTextByTitleOrAuthorContaining(searchString);
                else if(searchParameters.getPublisher() == true)
                    searchResult = reservableService.findTextByAuthorOrPublisherContaining(searchString);
                else
                    searchResult = reservableService.findTextByAuthorContaining(searchString);
            }
            else if(searchParameters.getTitle() == true){
                if(searchParameters.getPublisher() == true)
                    searchResult = reservableService.findTextByTitleOrPublisherContaining(searchString);
                else
                    searchResult = reservableService.findTextByTitleContaining(searchString);
            }
            else
                searchResult = reservableService.findTextByPublisherContaining(searchString);

            if(searchResult.size() > 0)
                for (int i = 0; i < searchResult.size(); i++)
                    System.out.println(searchResult.get(i).getTitle());

            modelAndView.addObject("searchResult", searchResult);

        }
        else
            System.out.print("empty search string");

        return modelAndView;
    }
}

package com.securde.controller;

import com.securde.model.reservable.SearchParameters;
import com.securde.model.reservable.Text;
import com.securde.service.ReservableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by avggo on 7/1/2017.
 */

@Controller
public class SearchController {
    @Autowired
    ReservableService reservableService;

    @RequestMapping(value = {"/search", "/"}, method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        /*ArrayList<String> uniqueAuthors = reservableService.findDistinctAuthors();
        ArrayList<String> uniqueBooks = reservableService.findDistinctBooks();
        ArrayList<String> uniqueMagazines = reservableService.findDistinctMagazines();
        ArrayList<String> uniqueThesis = reservableService.findDistinctThesis();
        ArrayList<String> uniquePublishers = reservableService.findDistinctPublishers();*/

        /*modelAndView.addObject("uniqueAuthors", uniqueAuthors);
        modelAndView.addObject("uniqueBooks", uniqueBooks);
        modelAndView.addObject("uniqueMagazines", uniqueMagazines);
        modelAndView.addObject("uniqueThesis", uniqueThesis);
        modelAndView.addObject("uniquePublishers", uniquePublishers);*/

        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("searchParameters", searchParameters);

        return modelAndView;
    }

    @RequestMapping(value = {"/authors"}, method = RequestMethod.GET)
    public ModelAndView authorCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<String> uniqueAuthors = reservableService.findDistinctAuthors();
        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("searchParameters", searchParameters);
        modelAndView.addObject("uniqueAuthors", uniqueAuthors);

        System.out.print("AUTHOR");

        return modelAndView;
    }

    @RequestMapping(value = {"/publishers"}, method = RequestMethod.GET)
    public ModelAndView publisherCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<String> uniquePublishers = reservableService.findDistinctPublishers();
        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("searchParameters", searchParameters);
        modelAndView.addObject("uniquePublishers", uniquePublishers);

        System.out.print("PUBLISHER");

        return modelAndView;
    }

    @RequestMapping(value = {"/books"}, method = RequestMethod.GET)
    public ModelAndView bookCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> books = reservableService.findAllBooks();
        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("searchParameters", searchParameters);
        modelAndView.addObject("searchResult", books);

        System.out.print("BOOK");

        return modelAndView;
    }

    @RequestMapping(value = {"/thesis"}, method = RequestMethod.GET)
    public ModelAndView thesisCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> thesis = reservableService.findAllThesis();
        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("searchParameters", searchParameters);
        modelAndView.addObject("searchResult", thesis);

        System.out.print("THESIS");

        return modelAndView;
    }

    @RequestMapping(value = {"/magazines"}, method = RequestMethod.GET)
    public ModelAndView magazineCategory() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> magazine = reservableService.findAllMagazines();
        SearchParameters searchParameters = new SearchParameters();
        modelAndView.addObject("searchParameters", searchParameters);
        modelAndView.addObject("searchResult", magazine);

        System.out.print("magazine");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/author"}, method = RequestMethod.GET)
    public ModelAndView authorBookSearch(@RequestParam("name") String authorName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> searchResult;

        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setSearchString(authorName);
        searchParameters.setAuthor(true);
        modelAndView.addObject("searchParameters", searchParameters);

        searchResult = reservableService.findTextByAuthorContaining(authorName);

        modelAndView.addObject("searchResult", searchResult);

        System.out.println("AUTHOR2");

        return modelAndView;
    }

    @RequestMapping(value = {"/search/publisher"}, method = RequestMethod.GET)
    public ModelAndView publisherBookSearch(@RequestParam("name") String publisherName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        ArrayList<Text> searchResult;

        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setSearchString(publisherName);
        searchParameters.setPublisher(true);
        modelAndView.addObject("searchParameters", searchParameters);

        searchResult = reservableService.findTextByPublisherContaining(publisherName);

        modelAndView.addObject("searchResult", searchResult);

        System.out.println("PUBLISHER2");

        return modelAndView;
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public ModelAndView retrieveSearch(SearchParameters searchParameters, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");

        /*ArrayList<String> uniqueAuthors = reservableService.findDistinctAuthors();
        ArrayList<String> uniqueBooks = reservableService.findDistinctBooks();
        ArrayList<String> uniqueMagazines = reservableService.findDistinctMagazines();
        ArrayList<String> uniqueThesis = reservableService.findDistinctThesis();
        ArrayList<String> uniquePublishers = reservableService.findDistinctPublishers();

        modelAndView.addObject("uniqueAuthors", uniqueAuthors);
        modelAndView.addObject("uniqueBooks", uniqueBooks);
        modelAndView.addObject("uniqueMagazines", uniqueMagazines);
        modelAndView.addObject("uniqueThesis", uniqueThesis);
        modelAndView.addObject("uniquePublishers", uniquePublishers);*/

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
            else if(searchParameters.getPublisher() == true)
                searchResult = reservableService.findTextByPublisherContaining(searchString);
            else
                searchResult = reservableService.searchText(searchString);

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

package com.securde.service;

import com.securde.model.repository.TextRepository;
import com.securde.model.reservable.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by avggo on 6/29/2017.
 */

@Service("textService")
public class TextService {

    @Autowired
    private TextRepository textRepository;

    public ArrayList<Text> findTextByTitleContaining(String title) {
        return textRepository.findByTitleContaining(title);
    }

    public ArrayList<Text> search(@Param("searchParam") String searchParam){
        return textRepository.search(searchParam);
    }
}

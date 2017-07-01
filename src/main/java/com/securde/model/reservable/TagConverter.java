package com.securde.model.reservable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by kevin on 7/1/2017.
 */
@Converter
public class TagConverter implements AttributeConverter<ArrayList<String>, String> {

    @Override
    public String convertToDatabaseColumn(ArrayList<String> attribute) {
        return String.join(",", attribute);
    }

    @Override
    public ArrayList<String> convertToEntityAttribute(String dbData) {
        return new ArrayList<>(Arrays.asList(dbData.split(",")));
    }
}

package com.smartdigit.zalewski.gamecenter.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartdigit.zalewski.gamecenter.domain.Fleet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

@Component
public class HashMapConverter implements AttributeConverter<Map<Long, Fleet>, String> {

    private final ObjectMapper objectMapper;

    @Autowired
    public HashMapConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(Map<Long, Fleet> fleets) {
        String fleetsJson = null;

        try {
            fleetsJson = objectMapper.writeValueAsString(fleets);
        } catch (IOException e){
            System.out.println("Couldn't write map to json: " + e.getMessage());
        }
        return fleetsJson;
    }

    @Override
    public Map<Long, Fleet> convertToEntityAttribute(String fleetsJson) {
        Map<Long,Fleet> fleets = null;
        try {
            fleets = objectMapper.readValue(fleetsJson, Map.class);
        } catch (IOException e) {
            System.out.println("Couldn't read json to map " + e.getMessage());
        }
        return fleets;
    }
}

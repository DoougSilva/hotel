package com.desafio.hotel.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {

    private ObjectMapper mapper = new ObjectMapper();
    JavaTimeModule module = new JavaTimeModule();


    public byte[] convertBytes(Object object) throws JsonProcessingException {
        mapper.registerModule(module);
        return mapper.writeValueAsBytes(object);
    }
}

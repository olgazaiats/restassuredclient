package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class CarMapDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String keyMap, DeserializationContext deserializationContext) {
        String[] cars = keyMap.split("and");
        String first = cars[0].trim();
        String secondString = cars[1].trim();
        int second = Integer.parseInt(secondString);
        return new CarMap(first, second);
    }
}

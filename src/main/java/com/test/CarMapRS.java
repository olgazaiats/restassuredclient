package com.test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Map;

@Data
public class CarMapRS {
    @JsonDeserialize(keyUsing = CarMapDeserializer.class)
    private Map<CarMap, String> carsMap;
}

package com.test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import java.util.Map;

@Data
public class MyPairRS {

    @JsonDeserialize(keyUsing = MyPairDeserializer.class)
    private Map<MyPair, String> map;


}

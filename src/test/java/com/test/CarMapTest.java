package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class CarMapTest {

    private RequestSpecification spec;

    @BeforeSuite
    public void setUp(){
        String baseURL = "http://localhost:8080/service";

        spec = new RequestSpecBuilder().setBaseUri(baseURL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void getCarsMapTest(){
        CarMapRS carsRS = given().spec(spec).expect().statusCode(200).when().get("detail/json/cars/map").as(CarMapRS.class);

        assertTrue(carsRS.getCarsMap().containsValue("BMW"));
        assertTrue(carsRS.getCarsMap().containsKey(new CarMap("BMW", 100)));
        assertTrue(carsRS.getCarsMap().containsValue("AUDI"));
        assertTrue(carsRS.getCarsMap().containsKey(new CarMap("AUDI", 100)));

//Better to use this approach!
        HashMap<CarMap, String> expectedMap = new HashMap<>();
        expectedMap.put(new CarMap("BMW", 100), "BMW");
        expectedMap.put(new CarMap("AUDI", 100), "AUDI");
        assertTrue(carsRS.getCarsMap().equals(expectedMap));


    }
}

package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static com.test.TestUtils.getCars;
import static io.restassured.RestAssured.given;

public class CarTest {
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
    public void getAllCarsTest(){
        List<Car> expectedCars = getCars();

        Cars cars = given().spec(spec).get("cars/xml").as(Cars.class);
        Assert.assertEquals(cars.getCar(), expectedCars);
    }



}

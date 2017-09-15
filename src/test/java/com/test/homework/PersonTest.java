package com.test.homework;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;
import static com.test.homework.TestUtils.getPeople;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;


public class PersonTest {

    private RequestSpecification spec;

    @BeforeSuite
    public void setUp(){
        String baseURL = "http://localhost:8080/service";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    @Test
    public void getPersonsVerificationTest(){
        List<Person> expectedPersons = getPeople();

        People people = given().spec(spec).get("persons/xml").as(People.class);
        assertEquals(people.getPerson(), expectedPersons);

    }


}

package com.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.test.TestUtils.getPeople;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserTest {

    private RequestSpecification spec;

    @BeforeSuite
    public void setUp(){
        String baseURL = "http://localhost:8080/service/";

        spec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    //Assert in body
    @Test
    public void getSingleUserTest(){
        given().spec(spec).
                expect().
                    statusCode(200).
                    body(
                            "email", equalTo("test@hascode.com"),
                            "firstName", equalTo("Tim"),
                            "lastName", equalTo("Testerman"),
                            "id", equalTo("1")).
                    when().
                    get("single-user");
    }

    @Test
    public void getSingleUserWithObjectMapper(){
        //Person expectedPerson = new Person();
                //expectedPerson.setEmail("test@hascode.com").setFirstName("Tim").setLastName("Testerman").setId(1);

        Person actualPerson = given().spec(spec).
                expect().
                    statusCode(200).
                    when().
                    get("single-user").as(Person.class);
        assertEquals(actualPerson, getPeople().get(0));
    }

    @Test
    public void getSingleUserXmlTest(){
        Person actualPerson = given().spec(spec).expect().statusCode(200).when().get("single-user/xml").as(Person.class);
        assertEquals(actualPerson, getPeople().get(0));
    }

    @Test
    public void getMultipleUsersWithObjectMapper(){
        List<Person> personsExpected = getPeople();
        String json = given().spec(spec).get("/persons/json").asString();
        JsonPath jp = new JsonPath(json);
        List<Person> personsActual = jp.getList("person", Person.class);
        assertEquals(personsActual, personsExpected);
    }

    @Test
    public void getMultipleUsersXmlTest(){
        List<Person> personsExpected = getPeople();

        People people = given().spec(spec).get("/persons/xml").as(People.class);
        assertEquals(people.getPerson(), personsExpected);
    }

    @Test
    public void getMultipleUsersJsonTest(){
        List<Person> expectedPersons = getPeople();

        People people = given().spec(spec).get("persons/json").as(People.class);
        assertEquals(people.getPerson(), expectedPersons);
    }

    @Test
    public void getJsonMapKeyValueTest(){
        MyPairRS pairRS = given().spec(spec).
                expect().statusCode(200).when().get("detail/json/map").as(MyPairRS.class);

        assertTrue(pairRS.getMap().containsValue("Comedy"));
        assertTrue(pairRS.getMap().containsKey(new MyPair("Abbott", "Costello")));

        HashMap<MyPair, String> expectedMap = new HashMap<>();
        expectedMap.put(new MyPair("Abbott", "Costello"), "Comedy");
        assertTrue(pairRS.getMap().equals(expectedMap));
    }

    @Test
    public void getPersonWithAuthenticationTest(){
        given().spec(spec).
        expect().statusCode(401).when().get("/secure/person");

        expect().statusCode(200).
                given().auth().basic("admin", "admin").get("/secure/person");
    }
}



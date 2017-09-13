package com.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class People {

    //use JsonProperty if name for list is different from tag name in Json
    @JsonProperty("person")
    private List<Person> person;


    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }
}

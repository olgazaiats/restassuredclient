package com.test.homework;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static List<Person> getPeople(){
        List<Person> people = new ArrayList<>();
        people.add(new Person("Tim", "Testerman", 1, "test@hascode.com"));
        people.add(new Person("Sara", "Stevens", 20, "dev@hascode.com"));
        people.add(new Person("Mark", "Mustache", 11, "devnull@hascode.com"));
        return people;
    }
}

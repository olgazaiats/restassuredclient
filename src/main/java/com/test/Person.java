package com.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Person {
    private String firstName;
    private String lastName;
    private int id;
    private String email;

}

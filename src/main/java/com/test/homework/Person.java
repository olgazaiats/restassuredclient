package com.test.homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person_root")
public class Person {

    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;
    private int id;
    @XmlElement(name = "email_field")
    private String email;

    public Person(){};
}

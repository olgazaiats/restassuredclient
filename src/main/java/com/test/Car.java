package com.test;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@XmlRootElement(name = "cars")
@EqualsAndHashCode
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlElement(name = "car_name")
    private String carName;
    @XmlElement(name = "car_speed")
    private int carSpeed;

    public Car(){
    }
}

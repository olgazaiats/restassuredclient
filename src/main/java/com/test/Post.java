package com.test;

import lombok.AllArgsConstructor;
import lombok.Data;
//import annotation that creates specific Equals and Hashcode methods
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
//add possibility for method to return objects
@Accessors(chain = true)
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}

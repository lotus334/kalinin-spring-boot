package com.springJdbcTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String id;
    private String name;
    private String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }
    // лишний код опущен
}
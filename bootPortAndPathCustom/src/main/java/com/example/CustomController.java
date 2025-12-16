package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }
}
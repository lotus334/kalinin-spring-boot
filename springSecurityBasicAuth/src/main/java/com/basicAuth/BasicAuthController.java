package com.basicAuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthController {

    @GetMapping("/")
    public String getMessage() {
        return "Hello World";
    }
}

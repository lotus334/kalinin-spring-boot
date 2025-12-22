package com.securityEntryPoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityEntryPointController {

    @GetMapping("/")
    public String home(){
        return "Welcome to Home Page";
    }
}

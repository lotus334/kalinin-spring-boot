package com.aspect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AspectApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AspectApp.class, args);
        UserService userService = context.getBean(UserService.class);
        userService.getUserById(1L);
    }
}

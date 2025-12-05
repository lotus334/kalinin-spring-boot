package com.example.spring.protorypeInSingleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        Singleton service = context.getBean(Singleton.class);

        if (Objects.equals(service.getPrototype(), service.getPrototype())) {
            System.out.println("Prototype is a prototype! Nonsense!");
        }

        if (Objects.equals(service.getPrototypeWithLookup(), service.getPrototypeWithLookup())) {
            throw new RuntimeException("Prototype is not prototype");
        }

        if (Objects.equals(service.getPrototypeWithFactory(), service.getPrototypeWithFactory())) {
            throw new RuntimeException("Prototype is not prototype");
        }

        if (Objects.equals(service.getPrototypeWithObjectProvider(), service.getPrototypeWithObjectProvider())) {
            throw new RuntimeException("Prototype is not prototype");
        }

        if (Objects.equals(service.getPrototypeWithContext(), service.getPrototypeWithContext())) {
            throw new RuntimeException("Prototype is not prototype");
        }
    }
}
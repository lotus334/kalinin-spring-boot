package com.beanInject;

import jakarta.inject.Inject;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Inject added in JSR-330
 * Example of Nullable and duplicated beans
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Inject
    private ServiceNormal serviceNormal;

    @Autowired
    private ServiceNormal serviceNormal2;

    @Autowired
    @Nullable
    private NullableService nullableService;

    @Inject
    @Nullable
    private NullableService nullableService2;


    @Autowired(required = false)
    private DublicatedServiceInterface dublicatedService;

//    @Inject
//    private DublicatedServiceInterface dublicatedService2;


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(serviceNormal.getMessage());
        System.out.println(serviceNormal2.getMessage());
        System.out.println(nullableService);
        System.out.println(nullableService2);
        System.out.println(dublicatedService);
    }
}

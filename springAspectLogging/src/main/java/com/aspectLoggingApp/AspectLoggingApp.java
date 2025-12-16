package com.aspectLoggingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectLoggingApp implements CommandLineRunner {

    @Autowired
    private EmployeeManager employeeManager;

    public static void main( String[] args ) {
        SpringApplication.run(AspectLoggingApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String employeeById = employeeManager.getEmployeeById(1L);
        System.out.println(employeeById);
    }
}

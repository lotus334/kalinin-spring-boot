package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GreeterSampleApplication implements CommandLineRunner {

//    @Autowired
//    private Greeter greeter;

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(GreeterSampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        boolean found = false;
        for (String bean : appContext.getBeanDefinitionNames()) {
            if (bean.contains("Greeter")) {
                System.out.println("            FOUNDED     !!!     " + appContext.getBean(bean).getClass());
//                String message = greeter.greet();
//                System.out.println(message);
                found = true;
            }
        }
        if (!found) {
            System.out.println("            NOT FOUNDED     !!!");
        }
    }
}

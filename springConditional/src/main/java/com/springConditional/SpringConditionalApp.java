package com.springConditional;

import com.springConditional.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringConditionalApp implements CommandLineRunner {

    private final BusinessService businessService;

    public static void main( String[] args ) {
        SpringApplication.run(SpringConditionalApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        businessService.doWork();
    }
}

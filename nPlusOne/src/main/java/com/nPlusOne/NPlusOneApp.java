package com.nPlusOne;

import com.nPlusOne.service.PopulateService;
import com.nPlusOne.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class NPlusOneApp implements CommandLineRunner {

    private final List<ReadService> services;

    private final PopulateService populateService;

    public static void main(String[] args) {
        SpringApplication.run(NPlusOneApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateService.populate();

        services.forEach(item -> {

            System.out.println("||| FOUNDED: " + item.readBooksFromAuthorsByDefault() + " |||");
            System.out.println("||| FOUNDED: " + item.readBooksFromAuthorsByExtended() + " |||");
            System.out.println("||| FOUNDED: " + item.readBooksFromAuthorsByExtended2() + " |||");
        });
    }
}

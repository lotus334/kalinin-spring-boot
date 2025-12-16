package com.hibernateExtendedLoggingP6Spy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class HibernateExtendedLoggingP6SpyApp implements CommandLineRunner {

    private final ItemRepository itemRepository;

    public static void main( String[] args ) {
        SpringApplication.run(HibernateExtendedLoggingP6SpyApp.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Hello World!");
        Item book = Item.builder().name("Book").build();
        itemRepository.save(book);
        System.out.println("Book saved!");
        System.out.println(itemRepository.findAll());
    }
}

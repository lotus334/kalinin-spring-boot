package com.bootRestGzip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

/**
 * TODO кажется не возвращает сжатый формат. Ответ как будто не сжат.
 */
@SpringBootApplication
@ServletComponentScan
public class BootRestGzipApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BootRestGzipApp.class, args);
    }

    @Autowired
    com.bootRestGzip.ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        itemRepository.save(new com.bootRestGzip.Item(null, "Item 1"));
        itemRepository.save(new com.bootRestGzip.Item(null, "Item 2"));
    }
}
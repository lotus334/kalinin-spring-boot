package com.beansInClass;

import com.beansInClass.beans.Author;
import com.beansInClass.beans.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Book book() {
        return new Book();
    }

    @Bean
    public Author author() {
        return new Author();
    }
}

package com.beanDeclaration.classConfigurationThroughMethod;

import com.beanDeclaration.classConfigurationThroughMethod.beans.Author;
import com.beanDeclaration.classConfigurationThroughMethod.beans.Book;
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

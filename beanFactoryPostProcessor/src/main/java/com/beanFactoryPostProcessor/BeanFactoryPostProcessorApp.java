package com.beanFactoryPostProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeanFactoryPostProcessorApp {
    public static void main(String[] args) {
        SpringApplication.run(BeanFactoryPostProcessorApp.class, args);
    }

    // STATIC !!!
    @Bean
    public static PrintBeanNameBeanFactoryPostProcessor getPrintBeanNameBeanFactoryPostProcessor() {
        return new PrintBeanNameBeanFactoryPostProcessor();
    }
}

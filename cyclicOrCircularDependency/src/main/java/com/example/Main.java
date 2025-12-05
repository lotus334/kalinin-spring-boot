package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        // Один из бинов использует Lazy-инициализацию
        BeanLazyA beanLazyA = context.getBean(BeanLazyA.class);
        BeanLazyB beanLazyB = context.getBean(BeanLazyB.class);
        // Доказать не получилось
        beanLazyA.delegateJob();
        beanLazyB.delegateJob();
    }
}
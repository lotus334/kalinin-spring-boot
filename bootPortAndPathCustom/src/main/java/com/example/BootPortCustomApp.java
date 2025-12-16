package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.servlet.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootPortCustomApp {
    public static void main( String[] args ) {
        SpringApplication.run(BootPortCustomApp.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/myapp");
    }
}

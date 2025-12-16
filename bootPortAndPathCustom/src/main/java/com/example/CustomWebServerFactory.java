package com.example;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.servlet.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomWebServerFactory implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        System.out.println("        PORT CHANGED    !!!");
        factory.setPort(8081);
    }
}

package com.customConfigurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

@SpringBootApplication
public class CustomConfigurerApp {

    public static void main(String[] args) {
        SpringApplication.run(CustomConfigurerApp.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Для использования фильтров нужен AuthenticationManager, а он доступен только после инициализации контекста. Здесь это не так.
        System.out.println("Есть ли в цепочке фильтров: " + http.getSharedObject(AuthenticationManager.class));

        http.apply(new MyConfigurer()).realmName("My custom realm name"); // использовать and() нельзя, поэтому не используем цепочку вызовов
        return http.build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/api/v4/greetings", request -> {
                    UserDetails userDetails = request.principal().map(Authentication.class::cast).map(Authentication::getPrincipal).map(UserDetails.class::cast).orElseThrow();
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Map.of("greeting", "Hello v4, %s".formatted(userDetails.getUsername())));
                })
                .build();
    }
}

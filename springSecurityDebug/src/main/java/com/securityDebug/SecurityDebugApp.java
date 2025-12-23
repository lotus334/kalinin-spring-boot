package com.securityDebug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

/**
 * curl -i http://localhost:8080/api/v1/greetings -X POST
 */
@SpringBootApplication
//@EnableWebSecurity(debug = true) // Покажет запросы и ответы
public class SecurityDebugApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDebugApp.class, args);
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

    /**
     * Пытаемся сломать доступ для ошибки 403
     * Здесь HttpSecurity - построитель контекста безопасности
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
//                .csrf(AbstractHttpConfigurer::disable) // Создаёт ошибку 403
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/v1/greetings").permitAll()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())

                // Тут нужно кастомизировать
//                .exceptionHandling(exceptions -> exceptions.accessDeniedHandler((requst, response, accessDeniedException) -> accessDeniedException.printStackTrace()))

                .build();
    }
}

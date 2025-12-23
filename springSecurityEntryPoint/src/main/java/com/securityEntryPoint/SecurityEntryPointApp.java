package com.securityEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@SpringBootApplication
public class SecurityEntryPointApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityEntryPointApp.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        // Entry point для Basic Authentication с помощью формы ввода браузера
        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("Basic Realm");

        return http
                .httpBasic(auth -> {
                    // Custom Entry Point, который выводит сообщение об ошибке и вызывает BasicAuthenticationEntryPoint. По умолчанию, Spring Security использует тут BasicAuthenticationEntryPoint.
                    AuthenticationEntryPoint firstEntiryPoint = (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {
                        authException.printStackTrace();
                        basicAuthenticationEntryPoint.commence(request, response, authException);
                    };
                    auth.authenticationEntryPoint(firstEntiryPoint);
                })
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                // Можно передать HttpStatusEntryPoint, тогда будет возвращаться статус ошибки после логирования стека ошибки
                .exceptionHandling(auth -> auth.authenticationEntryPoint(basicAuthenticationEntryPoint))

                .build();

    }
}

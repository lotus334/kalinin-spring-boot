package com.securityFilter.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class NewHexConfigurer extends AbstractHttpConfigurer<NewHexConfigurer, HttpSecurity> {

    private AuthenticationEntryPoint entryPoint
            = (request, response, authException) -> {
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Hex");
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    };

    @Override
    public void init(HttpSecurity builder) {
        builder.exceptionHandling(c -> c.authenticationEntryPoint(entryPoint)); // Задаём по умолчанию
        super.init(builder);
    }

    @Override
    public void configure(HttpSecurity builder) {
        final var authManager = builder.getSharedObject(AuthenticationManager.class);
        final var authFilter = new AuthenticationFilter(authManager, new NewHexAuthenticationConverter());

        // Настраиваем фильтр для работы с REST, т.к. он по умолчанию работает с веб-формами
        authFilter.setSuccessHandler((request, response, authentication) -> {
        }); // Не нужно отдавать форму. Продолжаем работу
        authFilter.setFailureHandler(new AuthenticationEntryPointFailureHandler(entryPoint));

        builder.addFilterBefore(authFilter, BasicAuthenticationFilter.class);
        super.configure(builder);
    }

    public NewHexConfigurer authenticationEntryPoint(AuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
        return this;
    }
}

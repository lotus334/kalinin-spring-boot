package com.securityFilter.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class HexConfigurer extends AbstractHttpConfigurer<HexConfigurer, HttpSecurity> {

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
        builder.addFilterBefore(new HexAuthenticationFilter(authManager, this.entryPoint), BasicAuthenticationFilter.class);
        super.configure(builder);
    }

    public HexConfigurer authenticationEntryPoint(AuthenticationEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
        return this;
    }
}

package com.basicAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class BasicAuthApp {
    public static void main(String[] args) {
        SpringApplication.run(BasicAuthApp.class, args);
    }

    /**
     * Stateless
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/").authenticated()  // Только корневой URL требует аутентификации
                )
                .httpBasic(Customizer.withDefaults());  // Включаем базовую HTTP‑аутентификацию

        return http.build();
    }
}

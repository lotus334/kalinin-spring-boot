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
     * Basic stateless или Form stateful используя Session, хотя можно JWT и тогда тоже stateless
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/").authenticated()  // Только корневой URL требует аутентификации
                )
                .formLogin(Customizer.withDefaults()); // Включаем форму входа
//                .httpBasic(Customizer.withDefaults());  // Включаем базовую HTTP‑аутентификацию

        return http.build();
    }
}

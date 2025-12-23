package com.securityFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

@SpringBootApplication
public class SecurityFilterApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityFilterApp.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        // Используя addFilter, фильтр должен иметь порядок согласно FilterOrderRegistration.java
        http.addFilterBefore(new DeniedClientFilter(), DisableEncodeUrlFilter.class)

                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}

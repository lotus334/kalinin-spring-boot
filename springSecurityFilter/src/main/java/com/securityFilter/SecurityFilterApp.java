package com.securityFilter;

import com.securityFilter.filter.HexConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * curl -i -H "Authorization: Hex user:c619d95d-74cf-45be-9945-55d129208d25" http://localhost:8080/
 */
@SpringBootApplication
public class SecurityFilterApp {
    public static void main(String[] args) {
        SpringApplication.run(SecurityFilterApp.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        // Используя addFilter, фильтр должен иметь порядок согласно FilterOrderRegistration.java
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
//                .addFilterBefore(new DisableEncodeUrlFilter(), DisableEncodeUrlFilter.class) // простой фильтр-пустышка
                .apply(new HexConfigurer());

        return http.build();
    }
}

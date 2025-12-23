package com.securityCustomUserDetailsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

@SpringBootApplication
public class CustomUserDetailsServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomUserDetailsServiceApp.class, args);
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailService(dataSource);
    }
}

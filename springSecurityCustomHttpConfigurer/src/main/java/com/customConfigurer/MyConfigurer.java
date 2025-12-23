package com.customConfigurer;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class MyConfigurer extends AbstractHttpConfigurer<MyConfigurer, HttpSecurity> {

    private String realmName = "MyRealm";

    @Override
    public void init(HttpSecurity builder) {
        System.out.println("Есть ли в методе инит в конфигураторе: " + builder.getSharedObject(AuthenticationManager.class));
        builder
                .httpBasic(httpBasic -> httpBasic.realmName(realmName))
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // Для примера добавляем аутентификацию
    }

    @Override
    public void configure(HttpSecurity builder) {
        // Для использования фильтров нужен AuthenticationManager, а он доступен только после инициализации контекста
        System.out.println("Есть ли в методе конфигур в конфигураторе: " + builder.getSharedObject(AuthenticationManager.class));

        // Нельзя добавлять конфигуратор в инициализированный контекст
        //        builder.httpBasic(httpBasic -> httpBasic.realmName("MyRealm"));
    }

    public MyConfigurer realmName(String realmName) {
        this.realmName = realmName;
        return this;
    }
}
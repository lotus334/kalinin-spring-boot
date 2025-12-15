package com.bootStarterCustomAutoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "baeldung.greeter")
@Getter
@Setter
public class GreeterProperties {
    private String userName;
    private String userMessage;
}
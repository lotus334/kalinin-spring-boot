package com.bootStarterCustomAutoconfigure;

import com.bootStarterCustom.Greeter;
import com.bootStarterCustom.GreetingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
@ConditionalOnProperty(prefix="baeldung.greeter", name="enabled", havingValue="true")
public class GreeterAutoConfiguration {

    @Autowired
    private GreeterProperties greeterProperties;

    @Bean
    @ConditionalOnMissingBean
    public GreetingConfig greetingConfig() {
        String userName = greeterProperties.getUserName() == null
                ? System.getProperty("user.name")
                : greeterProperties.getUserName();
        String message = greeterProperties.getUserMessage() == null
                ? System.getProperty("user.message")
                : greeterProperties.getUserMessage();
        GreetingConfig greetingConfig = new GreetingConfig();
        greetingConfig.put("USER_NAME", userName);
        greetingConfig.put("USER_MESSAGE", message);
        return greetingConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public Greeter greeter() {
        return new Greeter(greetingConfig());
    }
}
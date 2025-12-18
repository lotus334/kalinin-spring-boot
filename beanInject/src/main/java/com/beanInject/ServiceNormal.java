package com.beanInject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ServiceNormal {
    public String getMessage() {
        return "Hello from NormalService!";
    }
}

package com.beanInject;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class NullableService {
    public String getMessage() {
        return "Hello from NullableService";
    }
}

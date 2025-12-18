package com.beanInject;

import org.springframework.context.annotation.Scope;

//@Component
@Scope("prototype")
public class DublicatedServiceOne implements DublicatedServiceInterface {
    public String getMessage() {
        return "Hello from SpecialServiceOne";
    }
}

package com.beanInject;

import org.springframework.context.annotation.Scope;

//@Component
@Scope("prototype")
public class DublicatedServiceTwo implements DublicatedServiceInterface {
    public String getMessage() {
        return "Hello from SpecialServiceTwo";
    }
}

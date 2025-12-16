package com.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.aspect.UserService.getUserById(..))")
    public void logBefore() {
        System.out.println("Метод getUserById() будет вызван.");
    }
}
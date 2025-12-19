package com.nPlusOne;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    @Around("execution(* com.nPlusOne.service.ReadService.*(..))")
    public Object logRead(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("\n\n\n                                          START READING        !!! ");
        System.out.println("                    " + joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        System.out.println("                    " + joinPoint.getSignature());
        System.out.println("                                                END READING        !!!\n\n\n");
        return proceed;
    }
}

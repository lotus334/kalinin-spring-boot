package com.example.spring.protorypeInSingleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Prototype {

    private static final AtomicInteger ATOMIC_COUNTER = new AtomicInteger(0);

    public Prototype() {
        System.out.println("New ReportBuilder created: " + ATOMIC_COUNTER.incrementAndGet());
    }
}
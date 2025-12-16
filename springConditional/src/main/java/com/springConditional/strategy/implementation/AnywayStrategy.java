package com.springConditional.strategy.implementation;

import com.springConditional.strategy.BusinessStrategy;
import org.springframework.stereotype.Component;

@Component
public class AnywayStrategy implements BusinessStrategy {
    @Override
    public String getWork() {
        return "Anyway strategy work";
    }
}

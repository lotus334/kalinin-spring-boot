package com.springConditional.strategy.implementation;

import com.springConditional.strategy.BusinessStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.strategy", havingValue = "false")
public class FalseStrategy implements BusinessStrategy {
    @Override
    public String getWork() {
        return "False strategy work";
    }
}

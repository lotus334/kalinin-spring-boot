package com.springConditional.strategy.implementation;

import com.springConditional.strategy.BusinessStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.strategy", havingValue = "true")
public class TrueStrategy implements BusinessStrategy {
    @Override
    public String getWork() {
        return "True strategy work";
    }
}

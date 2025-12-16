package com.springConditional.service;

import com.springConditional.strategy.BusinessStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final List<BusinessStrategy> strategies;

    public void doWork() {
        for (BusinessStrategy strategy : strategies) {
            String work = strategy.getWork();
            System.out.println(work);
        }
    }
}
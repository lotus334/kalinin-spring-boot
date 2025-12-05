package com.example.spring.cyclicOrCircularDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BeanLazyB {
    private final BeanLazyA beanLazyA;

    @Autowired
    public BeanLazyB(@Lazy BeanLazyA beanLazyA) {
        System.out.println("BeanLazyA autowired");
        this.beanLazyA = beanLazyA;
    }

    // методы бесполезны
    public void delegateJob() {
        beanLazyA.doJob();
    }

    // методы бесполезны
    public void doJob() {
        System.out.println("beanLazyB done");
    }
}
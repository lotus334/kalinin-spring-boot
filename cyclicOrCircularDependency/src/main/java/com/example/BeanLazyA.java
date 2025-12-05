package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanLazyA {
    private final BeanLazyB beanLazyB;

    @Autowired
    public BeanLazyA(BeanLazyB beanLazyB) {
        System.out.println("BeanLazyB autowired");
        this.beanLazyB = beanLazyB;
    }

    // методы бесполезны
    public void delegateJob() {
        beanLazyB.doJob();
    }

    // методы бесполезны
    public void doJob() {
        System.out.println("beanLazyA done");
    }
}
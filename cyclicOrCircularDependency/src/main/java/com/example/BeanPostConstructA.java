package com.example;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * НЕ РАБОТАЕТ
 */
@Component
public class BeanPostConstructA {

    private BeanPostConstructB beanPostConstructB;

    @Autowired
    private ObjectFactory<BeanPostConstructB> objectFactory;

//    @PostConstruct
//    public void init() {
//        beanPostConstructB = objectFactory.getObject();
//    }
}
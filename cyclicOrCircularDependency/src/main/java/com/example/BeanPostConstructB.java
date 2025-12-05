package com.example;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * НЕ РАБОТАЕТ
 */
@Component
public class BeanPostConstructB {

    private BeanPostConstructA beanPostConstructA;

    @Autowired
    private ObjectFactory<BeanPostConstructA> objectFactory;

//    @PostConstruct
//    public void init() {
//        beanPostConstructA = objectFactory.getObject();
//    }
}

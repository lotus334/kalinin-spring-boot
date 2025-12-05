package com.example.spring.cyclicOrCircularDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * НЕ РАБОТАЕТ
 */
@Component
public class BeanSetterA {

    private BeanSetterB beanSetterB;

//    @Autowired
//    public void setBeanSetterB(BeanSetterB beanSetterB) {
//        System.out.println("BeanSetterB autowired");
//        this.beanSetterB = beanSetterB;
//    }
}

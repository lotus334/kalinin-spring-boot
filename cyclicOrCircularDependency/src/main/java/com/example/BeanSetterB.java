package com.example;

import org.springframework.stereotype.Component;

/**
 * НЕ РАБОТАЕТ
 */
@Component
public class BeanSetterB {

    private BeanSetterA beanSetterA;

//    @Autowired
//    public void setBeanSetterA(BeanSetterA beanSetterA) {
//        System.out.println("BeanSetterA autowired");
//        this.beanSetterA = beanSetterA;
//    }
}

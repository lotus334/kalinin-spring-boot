package com.example;

import org.springframework.stereotype.Component;

/**
 * НЕ РАБОТАЕТ
 * после spring framework 5 и boot 2.6 все эти хитрости с сеттером уже не работают. Spring заранее сканирует и запрещает это
 * если хочешь проверить именно пример с сеттерами, то добавь в properties: spring.main.allow-circular-references=true
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

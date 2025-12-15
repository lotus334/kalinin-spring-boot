package com.beanPostProcessorCustom;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppBeanPostProcessorExample {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppBeanPostProcessorExample.class);
        MainService mainService = context.getBean(MainService.class);
        System.out.println(mainService.getRandomInt());
    }
}

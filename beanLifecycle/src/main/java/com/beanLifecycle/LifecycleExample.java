package com.beanLifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class LifecycleExample {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifecycleExample.class);
        context.getBean(CompleteLifecycleBean.class);
        context.close();
    }

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public CompleteLifecycleBean completeLifecycleBean() {
        return new CompleteLifecycleBean();
    }
}

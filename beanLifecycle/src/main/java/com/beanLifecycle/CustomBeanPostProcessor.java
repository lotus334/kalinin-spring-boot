package com.beanLifecycle;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                PostConstruct annotation = method.getAnnotation(PostConstruct.class);
                System.out.println("3,5. postProcessBeforeInitialization in CustomBeanPostProcessor");
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                PostConstruct annotation = method.getAnnotation(PostConstruct.class);
                System.out.println("6,5. postProcessAfterInitialization in CustomBeanPostProcessor");
            }
        }
        return bean;
    }
}
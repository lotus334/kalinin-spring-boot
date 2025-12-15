package com.beanLifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CompleteLifecycleBean implements InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {

    private String beanName;
    private ApplicationContext context;

    public CompleteLifecycleBean() {
        System.out.println("1. Constructor: Создание экземпляра");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("2. BeanNameAware: Имя бина - " + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
        System.out.println("3. ApplicationContextAware: Контекст внедрен");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("4. @PostConstruct: Инициализация");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("5. InitializingBean.afterPropertiesSet: Инициализация");
    }

    public void customInit() {
        System.out.println("6. Кастомный init-method");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("7. @PreDestroy: Очистка перед уничтожением");
    }

    @Override
    public void destroy() {
        System.out.println("8. DisposableBean.destroy: Очистка");
    }

    public void customDestroy() {
        System.out.println("9. Кастомный destroy-method вызван");
    }
}
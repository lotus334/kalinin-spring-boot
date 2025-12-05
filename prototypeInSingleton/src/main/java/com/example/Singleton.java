package com.example;

import lombok.Getter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class Singleton {

    @Autowired
    private ObjectFactory<Prototype> prototypeFactory;

    @Autowired
    private ObjectProvider<Prototype> objectProvider;

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Getter
    private Prototype prototype;

    /**
     * Использование Lookup-аннотации
     */
    public Prototype getPrototypeWithLookup() {
         return getReportBuilder();
    }

    /**
     * Использование ObjectFactory
     */
    public Prototype getPrototypeWithFactory() {
        return prototypeFactory.getObject();
    }

    /**
     * Использование ObjectProvider
     */
    public Prototype getPrototypeWithObjectProvider() {
        return objectProvider.getObject();
    }

    public Prototype getPrototypeWithContext() {
        return context.getBean(Prototype.class);
    }

    // Для чего abstract - не знаю. Думаю он лишний, проверять лень.
    // Класс, кстати, тоже абстрактный
    @Lookup
    protected abstract Prototype getReportBuilder();
}

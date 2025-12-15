package com.qualifierCustom;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrinterType {
    String value() default "A0Printer";
}

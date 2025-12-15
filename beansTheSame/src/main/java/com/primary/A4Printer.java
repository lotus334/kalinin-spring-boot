package com.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("A4Printer")
@Primary
public class A4Printer implements Printer {
    @Override
    public String print() {
        return "A4";
    }
}

package com.qualifierCustom;

import org.springframework.stereotype.Component;

@Component("A4Printer")
public class A4Printer implements Printer {
    @Override
    public String print() {
        return "A4";
    }
}

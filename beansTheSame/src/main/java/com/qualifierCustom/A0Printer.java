package com.qualifierCustom;

import org.springframework.stereotype.Component;

@Component("A0Printer")
public class A0Printer implements Printer {
    @Override
    public String print() {
        return "A0";
    }
}

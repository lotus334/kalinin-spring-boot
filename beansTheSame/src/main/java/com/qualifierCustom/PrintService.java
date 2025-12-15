package com.qualifierCustom;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PrintService {

    @Autowired
    @PrinterType
    private Printer printer;
}

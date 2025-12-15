package com.qualifier;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PrintService {

    @Autowired
    @Qualifier("A4Printer")
    private Printer printer;
}

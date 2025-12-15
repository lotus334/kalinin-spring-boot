package com.qualifierCustom;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(App.class);
        System.out.println(
                annotationConfigApplicationContext.getBean(PrintService.class).getPrinter().print()
        );
    }
}

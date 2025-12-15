package com.beanDeclaration.mainConfigurationThroughPackage;

import com.beanDeclaration.mainConfigurationThroughPackage.beans.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.beanDeclaration.mainConfigurationThroughPackage")
public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Book myBookBean = context.getBean(Book.class);
        System.out.println(myBookBean.getAuthor().getAuthorName());    }
}

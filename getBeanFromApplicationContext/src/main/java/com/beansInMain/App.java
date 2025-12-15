package com.beansInMain;

import com.beansInMain.beans.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Book myBookBean = context.getBean(Book.class);
        System.out.println(myBookBean.getAuthor().getAuthorName());    }
}

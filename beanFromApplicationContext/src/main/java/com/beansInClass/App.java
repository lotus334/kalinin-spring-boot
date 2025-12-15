package com.beansInClass;

import com.beansInClass.beans.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Book myBookBean = context.getBean(Book.class);
        System.out.println(myBookBean.getAuthor().getAuthorName());    }
}

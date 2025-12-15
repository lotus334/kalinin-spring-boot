package com.beansByOne;

import com.beansByOne.beans.Author;
import com.beansByOne.beans.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Author.class);
        context.register(Book.class);
        context.refresh();

        Book myBookBean = context.getBean(Book.class);
        System.out.println(myBookBean.getAuthor().getAuthorName());    }
}

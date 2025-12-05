package com.example;

import com.example.entity.Child;
import com.example.entity.Parent;
import com.example.repository.ChildRepository;
import com.example.repository.ParentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        ParentRepository parentRepository = context.getBean(ParentRepository.class);
        ChildRepository childRepository = context.getBean(ChildRepository.class);

        Parent parent = new Parent();
        Child child = new Child();

        parentRepository.save(parent);
        childRepository.save(child);

        parent.setChild(child);
        child.setParent(parent);

        parentRepository.save(parent);
        childRepository.save(child);

        System.out.println("Try to find");

        // ЗДЕСЬ БУДЕТ StackOverflowError
//        System.out.println(parentRepository.findAll().iterator().next());
//        System.out.println(childRepository.findAll().iterator().next());

        System.out.println("Done");
    }
}

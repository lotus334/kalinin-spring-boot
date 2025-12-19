package com.nPlusOne.service;

import com.nPlusOne.entity.Author;
import com.nPlusOne.entity.Book;
import com.nPlusOne.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    public void populate() {
        System.out.println("                START POPULATE        !!!");

        List<String> authorsStr = List.of("Josh Long", "John Doe", "Jane Doe");

        List<String> booksStr = List.of("Spring Boot in Action", "Spring in Action", "Spring Boot in Action 2",
                "Java 8 in Action", "Java 9 in Action", "Java 10 in Action");
        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < authorsStr.size(); i++) {
            Author author = Author.builder().name(authorsStr.get(i)).build();
            List<Book> books = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                books.add(Book.builder().title(booksStr.get(i) + j).author(author).build());
                books.add(Book.builder().title(booksStr.get(i + authorsStr.size()) + j).author(author).build());
            }
            author.setBooks(books);
            authors.add(author);
        }
        authorRepository.saveAll(authors);
        System.out.println("                END POPULATE        !!!");
    }
}

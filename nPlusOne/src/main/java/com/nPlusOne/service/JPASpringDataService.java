package com.nPlusOne.service;

import com.nPlusOne.entity.Author;
import com.nPlusOne.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class JPASpringDataService implements ReadService {

    @Autowired
    AuthorRepository authorRepository;

    /**
     * Используя entityGraph избежим N+1
     */
    @Override
    public Integer readBooksFromAuthorsByDefault() {
        Iterable<Author> authors = authorRepository.findAll();
        System.out.println("||| Before getBooks |||");
        AtomicInteger i = new AtomicInteger();
        authors.forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
        return i.get();
    }

    /**
     * Используя fetch join
     */
    @Override
    public Integer readBooksFromAuthorsByExtended() {
        List<Author> authorsWithBooks = authorRepository.findAllWithBooksUsingFetchJoin();
        System.out.println("||| Before getBooks |||");
        AtomicInteger i = new AtomicInteger();
        authorsWithBooks.forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
        return i.get();
    }
}

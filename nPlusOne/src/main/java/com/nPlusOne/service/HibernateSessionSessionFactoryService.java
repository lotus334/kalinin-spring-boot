package com.nPlusOne.service;

import com.nPlusOne.entity.Author;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HibernateSessionSessionFactoryService implements ReadService{

    private final SessionFactory sessionFactory;

    /**
     * N+1
     */
    @Override
    public Integer readBooksFromAuthorsByDefault() {
        try (Session session = sessionFactory.openSession()) {
            Stream<Author> authorStream = session.createQuery("select a from Author a", Author.class)
                    .getResultStream();
            System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
            AtomicInteger i = new AtomicInteger();
            authorStream.forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
            return i.get();
        }
    }

    /**
     * Используем left join fetch
     */
    @Override
    public Integer readBooksFromAuthorsByExtended() {
        try (Session session = sessionFactory.openSession()) {
            Stream<Author> authorStream = session.createQuery("select distinct a from Author a left join fetch a.books", Author.class)
                    .getResultStream();
            System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
            AtomicInteger i = new AtomicInteger();
            authorStream.forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
            return i.get();
        }
    }
}

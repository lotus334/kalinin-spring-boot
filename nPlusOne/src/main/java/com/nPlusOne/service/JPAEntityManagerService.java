package com.nPlusOne.service;

import com.nPlusOne.entity.Author;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component
public class JPAEntityManagerService implements ReadService {

    @PersistenceContext // Spring подставляет прокси EntityManager, связанный с текущей транзакцией.
    private EntityManager entityManager;

    /**
     * N + 1
     */
    @Override
    public Integer readBooksFromAuthorsByDefault() {
        Stream<Author> authorStream = entityManager.createQuery("select a from Author a", Author.class).getResultStream();
        System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
        AtomicInteger i = new AtomicInteger();
        authorStream.forEach(author -> author.getBooks().forEach(item -> i.incrementAndGet()));
        return i.get();
    }

    /**
     * Использование EntityGraph
     */
    public Integer readBooksFromAuthorsByExtended() {
        EntityGraph<Author> graph = entityManager.createEntityGraph(Author.class);
        graph.addAttributeNodes("books");
        Stream<Author> authorStream = entityManager.createQuery("SELECT a FROM Author a", Author.class)
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultStream();
        System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
        AtomicInteger i = new AtomicInteger();
        authorStream.forEach(author -> author.getBooks().forEach(item -> i.incrementAndGet()));
        return i.get();
    }

    /**
     * Испльзование distinct left join fetch
     */
    public Integer readBooksFromAuthorsByExtended2() {
        Stream<Author> authorStream = entityManager.createQuery("select distinct a from Author a left join fetch a.books", Author.class).getResultStream();
        System.out.println("||| Before getBooks |||");
        AtomicInteger i = new AtomicInteger();
        authorStream.forEach(author -> author.getBooks().forEach(item -> i.incrementAndGet()));
        return i.get();
    }
}

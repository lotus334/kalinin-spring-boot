package com.nPlusOne.service;

import com.nPlusOne.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public class HibernateSessionEntityManagerService implements ReadService {

    @PersistenceContext // Spring подставляет прокси EntityManager, связанный с текущей транзакцией.
    private EntityManager entityManager;

    /**
     * N+1
     */
    @Override
    public Integer readBooksFromAuthorsByDefault() {
        Session session = entityManager.unwrap(Session.class);
        Stream<Author> authorStream = session.createQuery("select a from Author a", Author.class).getResultStream();
        System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
        AtomicInteger i = new AtomicInteger();
        authorStream.forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
        return i.get();
    }

    /**
     * Используя JOIN FETCH
     */
    @Override
    public Integer readBooksFromAuthorsByExtended() {
        Session session = entityManager.unwrap(Session.class);
        Stream<Author> authorStream = session.createQuery("select distinct a from Author a left join fetch a.books", Author.class).getResultStream();
        System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
        AtomicInteger i = new AtomicInteger();
        authorStream.forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
        return i.get();
    }

    @Override
    public Integer readBooksFromAuthorsByExtended2() {
        Session session = entityManager.unwrap(Session.class);
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        JpaRoot<Author> root = criteriaQuery.from(Author.class);
        root.fetch("books", JoinType.LEFT);
        criteriaQuery.select(root).distinct(true);

        Query<Author> query = session.createQuery(criteriaQuery);
        System.out.println("||| Before getBooks |||"); // TODO несмотря на EAGER, связи подгружаются только при вызове метода getBooks.
        AtomicInteger i = new AtomicInteger();
        query.getResultStream().forEach(author -> author.getBooks().forEach(item -> i.getAndIncrement()));
        return i.get();
    }
}
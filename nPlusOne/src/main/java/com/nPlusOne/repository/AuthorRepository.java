package com.nPlusOne.repository;

import com.nPlusOne.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("""
            select distinct a from Author a
            left join fetch a.books
            """)
    List<Author> findAllWithBooksUsingFetchJoin();

    @EntityGraph(attributePaths = "books")
    List<Author> findAll();
}

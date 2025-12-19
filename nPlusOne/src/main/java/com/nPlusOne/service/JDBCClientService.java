package com.nPlusOne.service;

import com.nPlusOne.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Здесь нет N+1
 */
@Service
@RequiredArgsConstructor
public class JDBCClientService implements ReadService{

    private final JdbcClient jdbcClient;

    @Override
    public Integer readBooksFromAuthorsByDefault() {
        List<Book> books = jdbcClient.sql("select id, title from book where author_id in (select id from author)")
                .query((rs, rowNum) -> Book.builder().id(rs.getLong("id")).title(rs.getString("title")).build())
                .list();
        return books.size();
    }
}

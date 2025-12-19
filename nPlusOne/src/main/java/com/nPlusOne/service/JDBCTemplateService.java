package com.nPlusOne.service;

import com.nPlusOne.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Здесь нет N+1
 */
@Service
@RequiredArgsConstructor
public class JDBCTemplateService implements ReadService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer readBooksFromAuthorsByDefault() {
        String sql = "select id, title from book where author_id in (select id from author)";

        List<Book> books = jdbcTemplate.query(sql, (rs, rowNum) -> Book.builder().id(rs.getLong("id")).title(rs.getString("title")).build());
        return books.size();
    }
}

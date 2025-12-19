package com.nPlusOne.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Здесь нет N+1
 */
@Service
@RequiredArgsConstructor
public class JDBCDataSourceService implements ReadService {

    private final DataSource dataSource;

    @Override
    public Integer readBooksFromAuthorsByDefault() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement authorStatement = connection.prepareStatement("select id, name from author");
            ResultSet authorResultSet = authorStatement.executeQuery();
            List<Long> authorIds = new ArrayList<>();
            while (authorResultSet.next()) {
                long id = authorResultSet.getLong("id");
                authorIds.add(id);
            }

            StringBuilder sqlBuilder = new StringBuilder("select id, title from book where author_id in (");
            authorIds.forEach(id -> {sqlBuilder.append("?,");});
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
            sqlBuilder.append(")");
            String sql = sqlBuilder.toString();

            PreparedStatement bookStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= authorIds.size(); i++) {
                bookStatement.setLong(i, authorIds.get(i - 1));
            }
            ResultSet bookResultSet = bookStatement.executeQuery();
            AtomicInteger i = new AtomicInteger();
            while (bookResultSet.next()) {
//                long id = bookResultSet.getLong("id");
//                String title = bookResultSet.getString("title");
                i.incrementAndGet();
            }
            return i.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

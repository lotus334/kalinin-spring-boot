package com.patternAdapter.inheritance;

import com.patternAdapter.FindTaskByIdSpi;
import com.patternAdapter.TaskData;
import org.springframework.jdbc.object.MappingSqlQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Через наследование
 * Пустышка, так как нужно задекларировать запрос к БД
 */
public class FindTaskByIdMappingSqlQuery extends MappingSqlQuery<TaskData> implements FindTaskByIdSpi {
    @Override
    protected TaskData mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskData(rs.getObject("id", UUID.class));
    }

    @Override
    public Optional<TaskData> findTaskById(UUID id) {
        return Optional.ofNullable(
                // обращение к методу адаптируемого класса
                this.findObjectByNamedParam(Map.of("id", id)));

    }
}

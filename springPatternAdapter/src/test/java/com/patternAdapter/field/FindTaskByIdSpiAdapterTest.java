package com.patternAdapter.field;

import com.patternAdapter.TaskData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class FindTaskByIdSpiAdapterTest {

    @Mock
    NamedParameterJdbcOperations jdbcOperations;

    @InjectMocks
    FindTaskByIdSpiAdapter adapter;

    @Test
    void findTaskById_CallsQuery_ReturnsOptional() {
        // given
        var id = UUID.randomUUID();
        var task = new TaskData(id);

        doReturn(List.of(task)).when(this.jdbcOperations)
                .query("select * from t_task where id = :id",
                        Map.of("id", id), this.adapter);

        // when
        var optional = this.adapter.findTaskById(id);

        // then
        Assertions.assertEquals(Optional.of(task), optional);
    }
}

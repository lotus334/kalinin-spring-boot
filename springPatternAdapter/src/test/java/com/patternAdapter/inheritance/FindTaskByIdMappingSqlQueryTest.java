package com.patternAdapter.inheritance;

import com.patternAdapter.TaskData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class FindTaskByIdMappingSqlQueryTest {

    @Spy
    FindTaskByIdMappingSqlQuery adapter;

    @Test
    void findTaskById_CallsFindObjectByNamedParam_ReturnsOptional() {
        // given
        var id = UUID.randomUUID();
        var task = new TaskData(id);

        Mockito.doReturn(task).when(this.adapter)
                .findObjectByNamedParam(Map.of("id", id));

        // when
        var optional = this.adapter.findTaskById(id);

        // then
        Assertions.assertEquals(Optional.of(task), optional);
    }

}
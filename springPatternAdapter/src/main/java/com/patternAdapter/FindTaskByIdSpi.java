package com.patternAdapter;

import java.util.Optional;
import java.util.UUID;

public interface FindTaskByIdSpi {

    Optional<TaskData> findTaskById(UUID id);
}

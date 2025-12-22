package com.patternAdapter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Адаптер - предоставляет новый класс
 * Декоратор - расширяет класс
 * Прокси - предоставляет тот же класс
 */
@Entity
@Table(name = "t_task")
@NoArgsConstructor
@AllArgsConstructor
public class TaskData {

    @Id
    private UUID id;
}

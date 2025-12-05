package com.example.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Builder
@EqualsAndHashCode(exclude = "value")
public class ItemCorrect {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private final Long id;

    private String value;
}

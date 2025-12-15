package com.beansInClass.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Getter
public class Book {

    @Autowired
    private Author author;
}

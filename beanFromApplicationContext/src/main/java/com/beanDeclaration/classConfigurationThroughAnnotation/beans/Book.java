package com.beanDeclaration.classConfigurationThroughAnnotation.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class Book {

    @Autowired
    private Author author;
}

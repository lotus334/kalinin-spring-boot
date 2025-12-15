package com.beanDeclaration.mainConfiguration.beans;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Author {
    private final String authorName;

    public Author() {
        this.authorName = "Mark Twain";
    }
}

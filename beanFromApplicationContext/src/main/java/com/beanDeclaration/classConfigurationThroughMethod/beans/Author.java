package com.beanDeclaration.classConfigurationThroughMethod.beans;

import lombok.Getter;

@Getter
public class Author {
    private final String authorName;

    public Author() {
        this.authorName = "Mark Twain";
    }
}

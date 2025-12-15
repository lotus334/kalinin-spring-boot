package com.beansByOne.beans;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component("mark")
public class Author {
    private final String authorName;

    public Author() {
        this.authorName = "Mark Twain";
    }
}

package com.nPlusOne.service;

import org.springframework.transaction.annotation.Transactional;

public interface ReadService {

    @Transactional
    Integer readBooksFromAuthorsByDefault();

    @Transactional
    default Integer readBooksFromAuthorsByExtended() {
        return null;
    }

    @Transactional
    default Integer readBooksFromAuthorsByExtended2() {
        return null;
    }
}

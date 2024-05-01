package com.bookshop.catalogservice.domain;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("A book with ISBN " + isbn + " was not found");
    }
}

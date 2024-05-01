package com.bookshop.catalogservice.web.dto;

import com.bookshop.catalogservice.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {

    private String isbn;
    private String title;
    private String author;
    private Double price;

    public static BookResponse fromBook(Book book) {
        return new BookResponse(
            book.getIsbn(),
            book.getTitle(),
            book.getAuthor(),
            book.getPrice()
        );
    }
}

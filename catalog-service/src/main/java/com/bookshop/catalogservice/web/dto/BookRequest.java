package com.bookshop.catalogservice.web.dto;

import com.bookshop.catalogservice.domain.Book;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "ISBN is required")
    @Pattern(
        regexp = "^([0-9]{10}|[0-9]{13})$",
        message = "Invalid ISBN"
    )
    private String isbn;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    public Book toBook() {
        return Book.builder().isbn(isbn).title(title).author(author).price(price).build();
    }
}

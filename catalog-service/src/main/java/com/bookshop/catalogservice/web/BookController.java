package com.bookshop.catalogservice.web;

import com.bookshop.catalogservice.domain.BookService;
import com.bookshop.catalogservice.web.dto.BookRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(bookService.getBookList(), HttpStatus.OK);
    }

    @GetMapping("{isbn}")
    public ResponseEntity<Object> getByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(bookService.getBookDetails(isbn), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.addBookToCatalog(bookRequest.toBook()),
            HttpStatus.CREATED);
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Object> delete(@PathVariable String isbn) {
        bookService.removeBook(isbn);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Object> put(@PathVariable String isbn,
        @Valid @RequestBody BookRequest bookRequest) {
        return new ResponseEntity<>(bookService.updateBookDetails(isbn, bookRequest.toBook()),
            HttpStatus.OK);
    }
}

package com.bookshop.catalogservice.domain;

import com.bookshop.catalogservice.web.dto.BookResponse;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<BookResponse> getBookList() {
        return bookRepository.findAll().stream().map(BookResponse::fromBook).toList();
    }

    public BookResponse getBookDetails(String isbn) {
        return BookResponse.fromBook(bookRepository.findByIsbn(isbn)
            .orElseThrow(() -> new BookNotFoundException(isbn)));
    }

    public BookResponse addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        return BookResponse.fromBook(bookRepository.save(book));
    }

    @Transactional
    public void removeBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public BookResponse updateBookDetails(String isbn, Book book) {
        Book bookFromDB = bookRepository.findByIsbn(isbn)
            .orElseGet(() -> bookRepository.save(book));
        bookFromDB.updateDetails(book);
        return BookResponse.fromBook(bookFromDB);
    }
}

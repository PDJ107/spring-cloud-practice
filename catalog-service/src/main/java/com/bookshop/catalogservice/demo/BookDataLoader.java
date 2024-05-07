package com.bookshop.catalogservice.demo;

import com.bookshop.catalogservice.domain.Book;
import com.bookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = Book.builder().isbn("1234567891").title("Northern Lights")
            .author("Lyra Silverstar").price(9.90).build();
        var book2 = Book.builder().isbn("1234567892").title("Polar Journey")
            .author("Iorek Polarson").price(12.90).build();
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}

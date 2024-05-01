package com.bookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookshop.catalogservice.web.dto.BookRequest;
import com.bookshop.catalogservice.web.dto.BookResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void BookCreateTest() {
        var bookRequest = new BookRequest("1234567890", "test title", "test author", 100.0);
        var expected = new BookResponse("1234567890", "test title", "test author", 100.0);

        webTestClient.post()
            .uri("/books")
            .bodyValue(bookRequest)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(BookRequest.class).value(actual -> {
                assertThat(actual).isNotNull();
                assertThat(actual.getIsbn()).isEqualTo(expected.getIsbn());
            });
    }
}

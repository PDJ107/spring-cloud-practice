package com.bookshop.catalogservice.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookshop.catalogservice.web.dto.BookRequest;
import com.bookshop.catalogservice.web.dto.BookResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<BookRequest> requestJson;

    @Autowired
    private JacksonTester<BookResponse> responseJson;

    @Test
    void serializeTest() throws Exception {
        var bookResponse = new BookResponse("1234567890", "test title", "test author", 100.0);
        var jsonContent = responseJson.write(bookResponse);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
            .isEqualTo(bookResponse.getIsbn());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
            .isEqualTo(bookResponse.getTitle());
        assertThat(jsonContent).extractingJsonPathStringValue("@.author")
            .isEqualTo(bookResponse.getAuthor());
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price")
            .isEqualTo(bookResponse.getPrice());
    }

    @Test
    void deserializeTest() throws Exception {
        var content = """
                {
                    "isbn": "1234567890",
                    "title": "test title",
                    "author": "test author",
                    "price": 100.0
                }
            """;
        assertThat(requestJson.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(new BookRequest("1234567890", "test title", "test author", 100.0));
    }
}

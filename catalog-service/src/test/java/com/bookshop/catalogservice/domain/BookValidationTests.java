package com.bookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookshop.catalogservice.web.dto.BookRequest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void successTest() {
        var bookRequest = new BookRequest("1234567890", "test title", "test author", 100.0);
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(bookRequest);
        assertThat(violations).isEmpty();
    }

    @Test
    void failureWrongRequestTest() {
        var bookRequest = new BookRequest("a234567890", "test title", "test author", 100.0);
        Set<ConstraintViolation<BookRequest>> violations = validator.validate(bookRequest);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Invalid ISBN");
    }
}

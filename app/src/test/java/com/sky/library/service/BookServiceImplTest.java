package com.sky.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.sky.library.BookRepositoryStub;
import com.sky.library.dao.BookRepository;
import com.sky.library.model.dto.BookDTO;

import org.junit.Before;
import org.junit.Test;

public class BookServiceImplTest {
    private static final String BOOK_PREFIX = "BOOK-";
    private static final String BOOK_PREFIX_INVALID = "REFERENCE-";
    private static final String GRUFFALO_REFERENCE = BOOK_PREFIX + "GRUFF472";
    private static final String POOH_REFERENCE = BOOK_PREFIX + "POOH222";
    private static final String WILL_REFERENCE = BOOK_PREFIX + "WILL987";

    private static final String GRUFFALO_REFERENCE_UNKNOWN = BOOK_PREFIX + "999";
    private static final String GRUFFALO_REFERENCE_INVALID = BOOK_PREFIX_INVALID + "GRUFF472";
    private static final String GRUFFALO_TITLE = "The Gruffalo";
    private static final String GRUFFALO_REVIEW = "A mouse taking a walk in the woods";

    private BookService bookService;
    private BookRepository bookRepository;

    @Before
    public void setup() {
        this.bookRepository = new BookRepositoryStub();
        this.bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void shouldValidateBookReference() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.retrieveBook(GRUFFALO_REFERENCE_INVALID))
                .withMessage(String.format("invalid book reference value: %s", GRUFFALO_REFERENCE_INVALID))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldThrowExceptionIfBookReferenceDoesNotExist() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.retrieveBook(GRUFFALO_REFERENCE_UNKNOWN))
                .withMessage(String.format("book with reference value: %s does not exist", GRUFFALO_REFERENCE_UNKNOWN))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldRetrieveBook() {
        final BookDTO result = bookService.retrieveBook(GRUFFALO_REFERENCE);

        assertThat(result).isNotNull();
        assertThat(result.getReference()).isEqualTo(GRUFFALO_REFERENCE);
        assertThat(result.getTitle()).isEqualTo(GRUFFALO_TITLE);
        assertThat(result.getReview()).isEqualTo(GRUFFALO_REVIEW);
    }

    @Test
    public void shouldGetBookSummaryValidateBookReference() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.getBookSummary(GRUFFALO_REFERENCE_INVALID))
                .withMessage(String.format("invalid book reference value: %s", GRUFFALO_REFERENCE_INVALID))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldGetBookSummaryThrowExceptionIfBookReferenceDoesNotExist() {
        assertThatExceptionOfType(BookNotFoundException.class)
                .isThrownBy(() -> bookService.getBookSummary(GRUFFALO_REFERENCE_UNKNOWN))
                .withMessage(String.format("book with reference value: %s does not exist", GRUFFALO_REFERENCE_UNKNOWN))
                .withStackTraceContaining("BookNotFoundException")
                .withNoCause();
    }

    @Test
    public void shouldGetBookSummary() {
        final String resultGruffalo = bookService.getBookSummary(GRUFFALO_REFERENCE);
        assertThat(resultGruffalo).isNotNull();
        final String resultPooh = bookService.getBookSummary(POOH_REFERENCE);
        assertThat(resultPooh).isNotNull();
        final String resultWill = bookService.getBookSummary(WILL_REFERENCE);
        assertThat(resultWill).isNotNull();
    }
}

package ru.otus.amezgin.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.domain.Book;
import ru.otus.amezgin.library.exception.BookRemoveException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

@DisplayName("The BookServiceImpl class")
@SpringBootTest
class BookServiceImplTest {

    public static final int ID_ONE = 1;
    public static final int EXPECTED_LIST_BOOK_SIZE = 3;
    public static final int ZERO = 0;
    public static final String BOOK_TITLE_1 = "Стальная крыса идет на войну";
    public static final String BOOK_TITLE_2 = "Стальная крыса поет блюз";
    public static final String AUTHOR_1 = "Гаррисон, Г.";

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @DisplayName("is checking getById method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingGetById() {
        Book expectedBook = new Book(ID_ONE, BOOK_TITLE_1, new Author(ID_ONE, AUTHOR_1), List.of(genreService.getById(ID_ONE).get()));
        Book actualBook = bookService.getById(ID_ONE).get();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("is checking getAll method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingGetAll() {
        List<Book> books = bookService.getAll();
        assertThat(books.size()).isEqualTo(EXPECTED_LIST_BOOK_SIZE);
    }

    @DisplayName("is checking save method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingSave() {
        Book expectedBook = bookService.save(new Book(BOOK_TITLE_2, new Author(ID_ONE, AUTHOR_1), List.of(genreService.getById(ID_ONE).get())));
        assertThat(expectedBook.getId()).isGreaterThan(ZERO);
        assertThat(expectedBook.getTitle()).isEqualTo(BOOK_TITLE_2);
    }

    @DisplayName("is checking update method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingUpdate() {
        Book expectedBook = bookService.getById(ID_ONE).get();
        expectedBook.setTitle(BOOK_TITLE_2);
        bookService.update(expectedBook);
        Book actualBook = bookService.getById(ID_ONE).get();
        assertThat(expectedBook.getTitle()).isEqualTo(actualBook.getTitle());
    }

    @DisplayName("is checking deleteById method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingDeleteById() throws BookRemoveException {
        Book book = bookService.getById(ID_ONE).get();
        assertThat(book.getId()).isEqualTo(ID_ONE);

        bookService.deleteById(ID_ONE);

        assertThat(bookService.getAll()).doesNotContain(book);
    }
}
package ru.otus.amezgin.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.domain.Book;
import ru.otus.amezgin.library.domain.Genre;
import ru.otus.amezgin.library.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
@DisplayName("The BookController class")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    public static final long AUTHOR_ID_1 = 1L;
    public static final String AUTHOR_1 = "Гарриссон Г.";
    public static final long GENRE_ID_1 = 1L;
    public static final String GENRE_1 = "Фантастика";
    public static final long BOOK_ID_1 = 1L;
    public static final String BOOK_1 = "Билл герой галактики";
    public static final String BOOK_2 = "Пришельцы, дары приносящие";

    @DisplayName("is checking getById method.")
    @Test
    void checkingGetById() throws Exception {
        Author author = new Author(AUTHOR_ID_1, AUTHOR_1);
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);
        Book book = new Book(BOOK_ID_1, BOOK_1, author, List.of(genre), new ArrayList<>());

        when(bookService.getById(anyLong())).thenReturn(Optional.of(book));

        String expectedResponse = objectMapper.writeValueAsString(book);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/book/1")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @DisplayName("is checking getAll method.")
    @Test
    void checkingGetAll() throws Exception {
        Author author = new Author(AUTHOR_ID_1, AUTHOR_1);
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);
        Book book1 = new Book(BOOK_ID_1, BOOK_1, author, List.of(genre), new ArrayList<>());
        Book book2 = new Book(BOOK_ID_1, BOOK_2, author, List.of(genre), new ArrayList<>());
        List<Book> books = List.of(book1, book2);
        when(bookService.getAll()).thenReturn(books);

        String expectedResponse = objectMapper.writeValueAsString(books);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/books")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @DisplayName("is checking saveBook method.")
    @Test
    void checkingSave() throws Exception {
        Author author = new Author(AUTHOR_ID_1, AUTHOR_1);
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);
        Book book = new Book(BOOK_ID_1, BOOK_1, author, List.of(genre), new ArrayList<>());
        when(bookService.save(any())).thenReturn(book);

        String expectedResponse = objectMapper.writeValueAsString(book);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/book")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @DisplayName("is checking updateBook method.")
    @Test
    void checkingUpdate() throws Exception {
        Author author = new Author(AUTHOR_ID_1, AUTHOR_1);
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);
        Book book = new Book(BOOK_ID_1, BOOK_1, author, List.of(genre), new ArrayList<>());
        when(bookService.update(any())).thenReturn(book);

        String expectedResponse = objectMapper.writeValueAsString(book);

        MvcResult mvcResult = mockMvc.perform(put("/api/v1/book")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @DisplayName("is checking deleteBook method.")
    @Test
    void checkingDelete() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/api/v1/book/1")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        Integer status = mvcResult.getResponse().getStatus();

        assertThat(status).isEqualTo(200);
    }
}

package ru.otus.amezgin.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("The AuthorController class")
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static final long AUTHOR_ID_1 = 1L;
    public static final long AUTHOR_ID_2 = 2L;
    public static final String AUTHOR_1 = "Гаррисон, Г.";
    public static final String AUTHOR_2 = "Перумов, Н.";

    @WithUserDetails("user")
    @DisplayName("is checking getById method.")
    @Test
    void checkingGetById() throws Exception {
        Author author = new Author(AUTHOR_ID_1, AUTHOR_1);

        String expectedResponse = objectMapper.writeValueAsString(author);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/author/1")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @WithUserDetails("user")
    @DisplayName("is checking getAll method.")
    @Test
    void checkingGetAll() throws Exception {
        Author author1 = new Author(AUTHOR_ID_1, AUTHOR_1);
        Author author2 = new Author(AUTHOR_ID_2, AUTHOR_2);
        List<Author> authors = List.of(author1, author2);

        String expectedResponse = objectMapper.writeValueAsString(authors);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/author")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @WithUserDetails("admin")
    @DisplayName("is checking saveAuthor method.")
    @Test
    void checkingSave() throws Exception {
        Author expectedAuthor = new Author(AUTHOR_ID_2, AUTHOR_2);

        String expectedResponse = objectMapper.writeValueAsString(expectedAuthor);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/author")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @WithUserDetails("user")
    @DisplayName("is checking saveAuthor method with invalid authorities.")
    @Test
    void checkingSaveWithInvalidAuthorities() throws Exception {
        Author expectedAuthor = new Author(AUTHOR_ID_2, AUTHOR_2);

        String expectedResponse = objectMapper.writeValueAsString(expectedAuthor);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/author")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @WithUserDetails("user")
    @DisplayName("is checking delete method with invalid authorities.")
    @Test
    void checkingDeleteWithInvalidAuthorities() throws Exception {
        Author expectedAuthor = new Author(AUTHOR_ID_2, AUTHOR_2);

        String expectedResponse = objectMapper.writeValueAsString(expectedAuthor);

        MvcResult mvcResult = mockMvc.perform(delete("/api/v1/author/1")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isForbidden())
                .andReturn();
    }
}

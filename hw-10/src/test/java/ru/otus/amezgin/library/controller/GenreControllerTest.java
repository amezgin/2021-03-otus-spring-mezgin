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
import ru.otus.amezgin.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("The GenreController class")
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public static final long GENRE_ID_1 = 1L;
    public static final long GENRE_ID_2 = 2L;
    public static final String GENRE_1 = "Фантастика";
    public static final String GENRE_2 = "Фентези";

    @WithUserDetails("user")
    @DisplayName("is checking getById method.")
    @Test
    void checkingGetById() throws Exception {
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);

        String expectedResponse = objectMapper.writeValueAsString(genre);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/genre/1")
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
        Genre genre1 = new Genre(GENRE_ID_1, GENRE_1);
        Genre genre2 = new Genre(GENRE_ID_2, GENRE_2);
        List<Genre> genres = List.of(genre1, genre2);

        String expectedResponse = objectMapper.writeValueAsString(genres);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/genre")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @WithUserDetails("admin")
    @DisplayName("is checking saveGenre method.")
    @Test
    void checkingSave() throws Exception {
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);

        String expectedResponse = objectMapper.writeValueAsString(genre);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/genre")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @WithUserDetails("user")
    @DisplayName("is checking saveGenre method with invalid authorities.")
    @Test
    void checkingSaveWithInvalidAuthorities() throws Exception {
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);

        String expectedResponse = objectMapper.writeValueAsString(genre);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/genre")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @WithUserDetails("user")
    @DisplayName("is checking delete method with invalid authorities.")
    @Test
    void checkingDeleteWithInvalidAuthorities() throws Exception {
        Genre genre = new Genre(GENRE_ID_1, GENRE_1);

        String expectedResponse = objectMapper.writeValueAsString(genre);

        MvcResult mvcResult = mockMvc.perform(delete("/api/v1/genre/1")
                .contentType("application/json")
                .content(expectedResponse))
                .andExpect(status().isForbidden())
                .andReturn();
    }
}

package ru.otus.amezgin.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.amezgin.library.domain.Genre;
import ru.otus.amezgin.library.repository.GenreJdbc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

@DisplayName("The GenreServiceImpl class")
@SpringBootTest
class GenreServiceImplTest {

    @MockBean
    private GenreJdbc genreJdbc;

    @Autowired
    private GenreServiceImpl genreService;

    public static final int ZERO_ID = 0;
    public static final int GENRE_ID_1 = 1;
    public static final int GENRE_ID_2 = 2;
    public static final String FANTASY = "Фентези";
    public static final String FANTASTIC = "Фантастика";
    public static final String NEW_GENRE = "Приключение";

    @DisplayName("is checking getById method.")
    @Test
    void checkingGetById() {
        Genre expectedGenre = new Genre(GENRE_ID_1, FANTASTIC);
        doReturn(Optional.of(expectedGenre)).when(genreJdbc).getById(GENRE_ID_1);
        Genre actualGenre = genreService.getById(GENRE_ID_1).get();

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("is checking getAll method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingGetAll() {
        Genre genre1 = new Genre(GENRE_ID_1, FANTASTIC);
        Genre genre2 = new Genre(GENRE_ID_2, FANTASY);
        List<Genre> list = List.of(genre1, genre2);

        doReturn(list).when(genreJdbc).getAll();

        List<Genre> actList = genreService.getAll();

        assertThat(actList).isEqualTo(list);
    }

    @DisplayName("is checking save method.")
    @Test
    void checkingSave() {
        Genre expectedGenre = new Genre(GENRE_ID_1, NEW_GENRE);
        doReturn(Optional.of(expectedGenre)).when(genreJdbc).getById(GENRE_ID_1);
        Genre actualGenre = genreService.save(expectedGenre);
        assertThat(actualGenre.getId()).isGreaterThan(ZERO_ID);
        assertThat(actualGenre.getGenreName()).isEqualTo(expectedGenre.getGenreName());
    }
}
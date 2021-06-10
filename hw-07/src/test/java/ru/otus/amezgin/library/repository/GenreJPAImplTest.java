package ru.otus.amezgin.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.amezgin.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

@DataJpaTest
@Import(GenreJPAImpl.class)
@DisplayName("The GenreJPAImpl class")
class GenreJPAImplTest {

    public static final String NEW_GENRE = "Приключения";
    public static final int EXPECTED_LIST_GENRES_SIZE = 3;
    public static final long ZERO = 0;
    public static final long GENRE_ID = 1;

    @Autowired
    private GenreJPA genreJPA;

    @DisplayName("is checking getById method.")
    @Test
    void checkingGetById() {
        Genre genre = new Genre();
        genre.setGenreName(NEW_GENRE);
        genreJPA.save(genre);
        assertThat(genreJPA.getById(genre.getId())).isNotEmpty();
    }

    @DisplayName("is checking getAll method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingGetAll() {
        Genre genre = new Genre();
        genre.setGenreName(NEW_GENRE);
        genreJPA.save(genre);
        List<Genre> genres = genreJPA.getAll();
        assertThat(genres.size()).isEqualTo(EXPECTED_LIST_GENRES_SIZE);
        assertThat(genres).contains(genre);
    }

    @DisplayName("is checking save method.")
    @Test
    void checkingSave() {
        Genre genre = new Genre();
        genre.setGenreName(NEW_GENRE);
        genreJPA.save(genre);
        assertThat(genre.getId()).isGreaterThan(ZERO);
    }

    @DisplayName("is checking deleteById method.")
    @Test
    void checkingDeleteById() {
        Genre genre = new Genre();
        genre.setGenreName(NEW_GENRE);
        genreJPA.save(genre);
        assertThat(genre.getId()).isGreaterThan(ZERO);

        genreJPA.deleteById(genre.getId());

        assertThat(genreJPA.getAll()).doesNotContain(genre);
    }
}
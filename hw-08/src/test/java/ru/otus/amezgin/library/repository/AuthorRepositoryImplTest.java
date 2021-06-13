package ru.otus.amezgin.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.amezgin.library.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

@DataJpaTest
@DisplayName("The AuthorJPAImpl class")
class AuthorRepositoryImplTest {

    public static final String NEW_AUTHOR = "Лукьяненко, С.";
    public static final Long AUTHOR_ID = 1L;
    public static final int EXPECTED_LIST_AUTHORS_SIZE = 3;
    public static final long ZERO_ID = 0;

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("is checking getById method.")
    @Test
    void checkingGetById() {
        Author author = new Author();
        author.setFullName(NEW_AUTHOR);
        authorRepository.save(author);
        assertThat(authorRepository.findById(author.getId())).isNotEmpty();
    }

    @DisplayName("is checking getAll method.")
    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void checkingGetAll() {
        Author author = new Author();
        author.setFullName(NEW_AUTHOR);
        authorRepository.save(author);
        List<Author> authors = authorRepository.findAll();
        assertThat(authors.size()).isEqualTo(EXPECTED_LIST_AUTHORS_SIZE);
        assertThat(authors).contains(author);
    }

    @DisplayName("is checking save method.")
    @Test
    void checkingSave() {
        Author author = new Author();
        author.setFullName(NEW_AUTHOR);
        authorRepository.save(author);
        assertThat(author.getId()).isGreaterThan(ZERO_ID);
    }

    @DisplayName("is checking deleteById method.")
    @Test
    void checkingDeleteById() {
        Author author = authorRepository.findById(AUTHOR_ID).get();
        assertThat(author.getId()).isEqualTo(AUTHOR_ID);

        authorRepository.deleteById(AUTHOR_ID);

        assertThat(authorRepository.findAll()).doesNotContain(author);
    }
}
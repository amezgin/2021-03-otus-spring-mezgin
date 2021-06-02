package ru.otus.amezgin.library.service;

import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.exception.AuthorRemoveException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> getById(long id);

    Optional<Author> getByName(String name);

    List<Author> getAll();

    Author save(Author author);
}

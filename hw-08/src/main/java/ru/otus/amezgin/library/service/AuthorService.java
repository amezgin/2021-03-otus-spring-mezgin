package ru.otus.amezgin.library.service;

import ru.otus.amezgin.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> getById(Long id);

    Optional<Author> getByName(String name);

    List<Author> getAll();

    Author save(Author author);

    void deleteById(Long id);
}

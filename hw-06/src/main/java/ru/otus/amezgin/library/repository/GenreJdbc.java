package ru.otus.amezgin.library.repository;

import ru.otus.amezgin.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreJdbc {

    Optional<Genre> getById(long id);

    Optional<Genre> getByName(String name);

    List<Genre> getAll();

    Genre save(Genre genre);
}

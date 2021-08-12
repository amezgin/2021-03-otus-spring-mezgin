package ru.otus.amezgin.library.service;

import ru.otus.amezgin.library.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> getById(Long id);

    Optional<Genre> getByName(String name);

    List<Genre> getAll();

    Genre save(Genre genre);

    void deleteById(Long id);
}

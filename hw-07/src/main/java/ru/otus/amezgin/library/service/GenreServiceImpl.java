package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.repository.GenreJPA;
import ru.otus.amezgin.library.domain.Genre;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreJPA genreJPA;

    @Override
    public Optional<Genre> getById(Long id) {
        return genreJPA.getById(id);
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return genreJPA.getByName(name);
    }

    @Override
    public List<Genre> getAll() {
        return genreJPA.getAll();
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return genreJPA.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        genreJPA.deleteById(id);
    }
}

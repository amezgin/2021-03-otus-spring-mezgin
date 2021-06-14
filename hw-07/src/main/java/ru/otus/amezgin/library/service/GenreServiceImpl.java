package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.amezgin.library.repository.GenreRepository;
import ru.otus.amezgin.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getById(Long id) {
        return genreRepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getByName(String name) {
        return genreRepository.getByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreRepository.getAll();
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}

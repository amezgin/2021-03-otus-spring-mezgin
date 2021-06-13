package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.amezgin.library.repository.GenreJPARepository;
import ru.otus.amezgin.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreJPARepository genreJPARepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getById(Long id) {
        return genreJPARepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genre> getByName(String name) {
        return genreJPARepository.getByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreJPARepository.getAll();
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        return genreJPARepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        genreJPARepository.deleteById(id);
    }
}

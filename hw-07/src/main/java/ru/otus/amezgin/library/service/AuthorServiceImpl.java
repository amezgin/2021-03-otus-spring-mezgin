package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.amezgin.library.repository.AuthorJPARepository;
import ru.otus.amezgin.library.domain.Author;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorJPARepository authorJPARepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getById(Long id) {
        return authorJPARepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getByName(String name) {
        return authorJPARepository.getByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorJPARepository.getAll();
    }

    @Transactional
    @Override
    public Author save(Author author) {
        return authorJPARepository.save(author);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        authorJPARepository.deleteById(id);
    }
}

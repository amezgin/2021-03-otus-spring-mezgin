package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.repository.AuthorJPA;
import ru.otus.amezgin.library.domain.Author;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorJPA authorJPA;

    @Override
    public Optional<Author> getById(Long id) {
        return authorJPA.getById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return authorJPA.getByName(name);
    }

    @Override
    public List<Author> getAll() {
        return authorJPA.getAll();
    }

    @Transactional
    @Override
    public Author save(Author author) {
        return authorJPA.save(author);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        authorJPA.deleteById(id);
    }
}

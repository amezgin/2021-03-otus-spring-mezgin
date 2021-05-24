package ru.otus.amezgin.library.service;

import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.exception.AuthorRemoveException;
import ru.otus.amezgin.library.repository.AuthorJdbc;
import ru.otus.amezgin.library.domain.Author;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorJdbc authorJdbc;

    public AuthorServiceImpl(AuthorJdbc authorJdbc) {
        this.authorJdbc = authorJdbc;
    }

    @Override
    public Optional<Author> getById(long id) {
        return authorJdbc.getById(id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        return authorJdbc.getByName(name);
    }

    @Override
    public List<Author> getAll() {
        return authorJdbc.getAll();
    }

    @Override
    public Author save(Author author) {
        return authorJdbc.save(author);
    }

    @Override
    public void deleteById(long id) throws AuthorRemoveException {
        if (authorJdbc.getById(id).isEmpty()) {
            throw new AuthorRemoveException(String.format("The author with ID %d was not found in the DB.", id));
        }
    }
}

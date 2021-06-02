package ru.otus.amezgin.library.service;

import ru.otus.amezgin.library.domain.Book;
import ru.otus.amezgin.library.exception.BookRemoveException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getById(long id);

    List<Book> getAll();

    Book save(Book book);

    void update(Book book);

    void deleteById(long id);
}

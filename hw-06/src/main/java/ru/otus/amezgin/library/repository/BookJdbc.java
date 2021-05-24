package ru.otus.amezgin.library.repository;

import ru.otus.amezgin.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookJdbc {

    Optional<Book> getById(long id);

    List<Book> getAll();

    Book save(Book book);

    void update(Book book);

    void deleteById(long id);
}

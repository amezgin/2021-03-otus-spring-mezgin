package ru.otus.amezgin.library.repository;

import ru.otus.amezgin.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookJPA {

    Optional<Book> getById(Long id);

    List<Book> getAll();

    Book save(Book book);

    Book update(Book book);

    void deleteById(Long id);
}

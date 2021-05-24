package ru.otus.amezgin.library.service;

import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.exception.BookRemoveException;
import ru.otus.amezgin.library.repository.BookJdbc;
import ru.otus.amezgin.library.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookJdbc bookJdbc;

    public BookServiceImpl(BookJdbc bookJdbc) {
        this.bookJdbc = bookJdbc;
    }

    @Override
    public Optional<Book> getById(long id) {
        return bookJdbc.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookJdbc.getAll();
    }

    @Override
    public Book save(Book book) {
        return bookJdbc.save(book);
    }

    @Override
    public void update(Book book) {
        bookJdbc.update(book);
    }

    @Override
    public void deleteById(long id) throws BookRemoveException {
        if (bookJdbc.getById(id).isEmpty()) {
            throw new BookRemoveException(String.format("The book with ID %d was not found in the DB.", id));
        } else {
            bookJdbc.deleteById(id);
        }
    }
}

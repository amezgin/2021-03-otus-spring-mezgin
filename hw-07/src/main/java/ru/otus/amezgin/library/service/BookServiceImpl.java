package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.repository.BookJPA;
import ru.otus.amezgin.library.domain.Book;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookJPA bookJPA;

    @Override
    public Optional<Book> getById(Long id) {
        return bookJPA.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookJPA.getAll();
    }

    @Transactional
    @Override
    public Book save(Book book) {
        return bookJPA.save(book);
    }

    @Transactional
    @Override
    public void update(Book book) {
        bookJPA.update(book);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookJPA.deleteById(id);
    }
}

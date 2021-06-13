package ru.otus.amezgin.library.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.amezgin.library.repository.BookJPARepository;
import ru.otus.amezgin.library.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookJPARepository bookJPARepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(Long id) {
        return bookJPARepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookJPARepository.getAll();
    }

    @Transactional
    @Override
    public Book save(Book book) {
        return bookJPARepository.save(book);
    }

    @Transactional
    @Override
    public void update(Book book) {
        bookJPARepository.update(book);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookJPARepository.deleteById(id);
    }
}

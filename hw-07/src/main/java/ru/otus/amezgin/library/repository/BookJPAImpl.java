package ru.otus.amezgin.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.amezgin.library.domain.Book;
import ru.otus.amezgin.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookJPAImpl implements BookJPA {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b ", Book.class).getResultList();
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        em.merge(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        Optional.ofNullable(em.find(Book.class, id)).ifPresent(em::remove);
    }
}

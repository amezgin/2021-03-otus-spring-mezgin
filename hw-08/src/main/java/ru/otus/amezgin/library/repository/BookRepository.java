package ru.otus.amezgin.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.otus.amezgin.library.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b join fetch b.author a")
    @Override
    List<Book> findAll();
}

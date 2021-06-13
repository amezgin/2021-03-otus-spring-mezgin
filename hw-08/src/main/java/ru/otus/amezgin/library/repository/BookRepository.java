package ru.otus.amezgin.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.amezgin.library.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

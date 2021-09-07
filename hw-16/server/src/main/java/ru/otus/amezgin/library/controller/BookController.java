package ru.otus.amezgin.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.amezgin.library.domain.Book;
import ru.otus.amezgin.library.messaging.MessageProducer;
import ru.otus.amezgin.library.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final MessageProducer messageProducer;

    @GetMapping(value = "/api/v1/book/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        Book book = bookService.getById(id).orElseThrow();
        messageProducer.send(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping(value = "/api/v1/book", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping(value = "/api/v1/book", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping(value = "/api/v1/book/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return ResponseEntity.ok(bookService.update(book));
    }

    @DeleteMapping(value = "/api/v1/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

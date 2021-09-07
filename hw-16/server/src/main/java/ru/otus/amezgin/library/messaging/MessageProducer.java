package ru.otus.amezgin.library.messaging;

import ru.otus.amezgin.library.domain.Book;

public interface MessageProducer {

    void send(Book book);
}

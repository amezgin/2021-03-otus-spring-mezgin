package ru.otus.amezgin.restclient.massaging;

import ru.otus.amezgin.restclient.dto.BookDto;

public interface MessageConsumer {

    void listen(BookDto bookDto);
}

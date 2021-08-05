package ru.otus.amezgin.restclient.service;

import ru.otus.amezgin.restclient.dto.BookDto;

public interface BookService {

    BookDto getBook(Long id);
}

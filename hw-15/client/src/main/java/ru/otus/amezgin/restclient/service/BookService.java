package ru.otus.amezgin.restclient.service;

import ru.otus.amezgin.restclient.dto.BookDto;
import ru.otus.amezgin.restclient.exception.ExternalAuthorizationException;

public interface BookService {

    BookDto getBook(Long id) throws ExternalAuthorizationException;
}

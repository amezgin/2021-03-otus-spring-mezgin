package ru.otus.amezgin.restclient.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import ru.otus.amezgin.restclient.dto.BookDto;
import ru.otus.amezgin.restclient.exception.ExternalAuthorizationException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final ExternalAuthorizationService externalAuthorizationService;

    private final ParamService paramService;

    private final RestOperations rest;

    @Override
    public BookDto getBook(Long id) throws ExternalAuthorizationException {
        log.debug("Book id = {}", id);
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + externalAuthorizationService.getToken());
        var response = rest.exchange(paramService.getUrl() + "book/" + id,
                HttpMethod.GET, new HttpEntity<String>(headers), BookDto.class);
        return response.getStatusCode().equals(HttpStatus.OK) && (response.getBody() != null) ?
                response.getBody() : null;
    }
}

package ru.otus.amezgin.restclient.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import ru.otus.amezgin.restclient.dto.BookDto;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final RestOperations rest;

    private String token;

    @Value("${library.url}")
    private String url;

    @Value("${library.userName}")
    private String userName;

    @Value("${library.userPass}")
    private String userPass;

    @Override
    public BookDto getBook(Long id) {
        log.debug("Book id = {}", id);
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getToken());
        BookDto bookDto = null;
        try {
            var response = rest.exchange(url + "book/" + id,
                    HttpMethod.GET, new HttpEntity<String>(headers), BookDto.class);
            bookDto = response.getStatusCode().equals(HttpStatus.OK) && (response.getBody() != null) ?
                    response.getBody() : null;
        } catch (Exception e){
            e.printStackTrace();
        }
        return bookDto;
    }

    private String getToken() {
        if (token == null) {
            var auth = userName + ":" + userPass;
            var headers = new HttpHeaders();
            var encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
            headers.set("Content-Type", "application/json");
            var body = "{\"login\":\"" + userName +
                    "\", \"password\":\"" + userPass +
                    "\"}";
            var authEntity = new HttpEntity<>(body, headers);

            try {
                var response = rest.exchange(url + "authenticate",
                        HttpMethod.POST, authEntity, String.class);
                if (response.getStatusCode().equals(HttpStatus.OK) && (response.getBody() != null)) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readValue(response.getBody(), JsonNode.class);
                    token = jsonNode.get("id_token").asText();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return token;
    }
}

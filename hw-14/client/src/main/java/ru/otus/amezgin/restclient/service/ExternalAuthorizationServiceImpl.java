package ru.otus.amezgin.restclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import ru.otus.amezgin.restclient.exception.ExternalAuthorizationException;

@Service
@RequiredArgsConstructor
public class ExternalAuthorizationServiceImpl implements ExternalAuthorizationService {

    private final ParamService paramService;

    private final RestOperations rest;

    private String token;

    private long startSession;

    @Override
    public String getToken() throws ExternalAuthorizationException {

        if (token == null) {
            startSession = System.currentTimeMillis();
            getRequestOnExternalService();
        } else if (System.currentTimeMillis() - startSession > paramService.getSessionTime()) {
            getRequestOnExternalService();
        }
        return token;
    }

    private void getRequestOnExternalService() throws ExternalAuthorizationException {
        ResponseEntity<String> response = rest.exchange(paramService.getUrl() + "authenticate",
                HttpMethod.POST, getAuthEntity(), String.class);
        if (response.getStatusCode().equals(HttpStatus.OK) && (response.getBody() != null)) {
            getTokenFromResponse(response);
        }
    }

    private HttpEntity<Object> getAuthEntity() {
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        var body = "{\"login\":\"" + paramService.getUserName() +
                "\", \"password\":\"" + paramService.getPassword() +
                "\"}";
        return new HttpEntity<>(body, headers);
    }

    private void getTokenFromResponse(ResponseEntity<String> responseEntity) throws ExternalAuthorizationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            token = objectMapper.readValue(responseEntity.getBody(), JsonNode.class).get("id_token").asText();
        } catch (JsonProcessingException e) {
            throw new ExternalAuthorizationException(String.format("An error occurred while trying to log in %s", e));
        }
    }
}

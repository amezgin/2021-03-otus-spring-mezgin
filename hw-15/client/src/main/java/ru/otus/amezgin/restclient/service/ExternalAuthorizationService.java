package ru.otus.amezgin.restclient.service;

import ru.otus.amezgin.restclient.exception.ExternalAuthorizationException;

public interface ExternalAuthorizationService {

    String getToken() throws ExternalAuthorizationException;
}

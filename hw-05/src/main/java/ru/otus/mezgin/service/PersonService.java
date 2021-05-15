package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.errors.ReadInputLineException;

public interface PersonService {

    Person createPerson() throws ReadInputLineException;
}

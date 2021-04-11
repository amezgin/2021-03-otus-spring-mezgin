package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Person;

public interface PersonService {

    void askFullName();

    Person createPerson(String fullName);
}

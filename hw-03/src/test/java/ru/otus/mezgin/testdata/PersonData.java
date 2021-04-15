package ru.otus.mezgin.testdata;

import ru.otus.mezgin.domain.Person;

public class PersonData {

    public final static String PERSON_NAME = "Jhon";

    public final static String PERSON_LASTNAME = "Dou";

    public static Person getPersonWithoutAnswers() {
        return new Person(PERSON_NAME, PERSON_LASTNAME);
    }
}

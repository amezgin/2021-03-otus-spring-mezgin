package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.TestResult;
import ru.otus.mezgin.testdata.PersonData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The PersonTest class")
public class PersonTest {

    @DisplayName("is creating person.")
    @Test
    void createPersonTest() {
        String fullName = "Jhon   Dou";
        IOProvider ioProvider = new IOProviderImpl();
        InOutService inOutService = new ConsoleService(ioProvider);
        PersonService personService = new PersonServiceImpl(inOutService);

        Person expectedPerson = PersonData.getPersonWithoutAnswers();

        Person actualPerson = personService.createPerson(fullName);

        assertEquals(expectedPerson, actualPerson);
    }
}

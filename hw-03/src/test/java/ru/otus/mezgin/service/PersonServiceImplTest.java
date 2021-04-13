package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.testdata.PersonData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("The PersonServiceImplTest class")
public class PersonServiceImplTest {

    @DisplayName("is creating person.")
    @Test
    void createPersonTest() {
        String fullName = "Jhon   Dou";
        InOutService inOutService = Mockito.mock(ConsoleService.class);
        PersonService personService = new PersonServiceImpl(inOutService);

        Person expectedPerson = PersonData.getPersonWithoutAnswers();

        Person actualPerson = personService.createPerson(fullName);

        assertEquals(expectedPerson, actualPerson);
    }
}

package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.errors.ReadInputLineException;
import ru.otus.mezgin.testdata.PersonData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@DisplayName("The PersonServiceImplTest class")
@SpringBootTest
public class PersonServiceImplTest {

    @Mock
    private InOutLocalizationWrapper inOutLocalizationWrapper;

    @InjectMocks
    private PersonServiceImpl personService;

    @DisplayName("is creating person.")
    @Test
    void createPersonTest() throws ReadInputLineException {

        doReturn("Jhon Dou").when(inOutLocalizationWrapper).readLine();

        Person expectedPerson = PersonData.getPersonWithoutAnswers();

        Person actualPerson = personService.createPerson();

        assertEquals(expectedPerson, actualPerson);
    }
}

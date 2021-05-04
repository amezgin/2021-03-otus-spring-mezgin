package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.errors.ReadInputLineException;
import ru.otus.mezgin.testdata.PersonData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@DisplayName("The PersonServiceImplTest class")
public class PersonServiceImplTest {

    @Mock
    private InOutService inOutService;

    @Mock
    private MessageSource messageSource;

    @Mock
    private QuizConfig quizConfig;

    @DisplayName("is creating person.")
    @Test
    void createPersonTest() throws ReadInputLineException {

        PersonService personService = new PersonServiceImpl(inOutService, messageSource, quizConfig);

        doReturn("Jhon Dou").when(inOutService).readLine();

        Person expectedPerson = PersonData.getPersonWithoutAnswers();

        Person actualPerson = personService.createPerson();

        assertEquals(expectedPerson, actualPerson);
    }
}

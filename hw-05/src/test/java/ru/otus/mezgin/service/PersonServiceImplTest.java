package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.errors.ReadInputLineException;
import ru.otus.mezgin.testdata.PersonData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("The PersonServiceImplTest class")
@SpringBootTest
public class PersonServiceImplTest {

    @MockBean
    private InOutLocalizationWrapper inOutLocalizationWrapper;

    @Autowired
    private PersonServiceImpl personService;

    @DisplayName("is creating person.")
    @Test
    void createPersonTest() throws ReadInputLineException {

        given(this.inOutLocalizationWrapper.readLine()).willReturn("Jhon Dou");

        Person expectedPerson = PersonData.getPersonWithoutAnswers();

        Person actualPerson = personService.createPerson();

        assertEquals(expectedPerson, actualPerson);
    }
}

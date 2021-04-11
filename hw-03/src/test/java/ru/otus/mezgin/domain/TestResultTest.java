package ru.otus.mezgin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.testdata.PersonData;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The TestResultTest class")
class TestResultTest {

    private final Person person = PersonData.getPersonWithoutAnswers();

    @DisplayName("is checking for pass test.")
    @Test
    void checkingPassTest() {
        int countRightAnswers = 4;
        int countPersonRightAnswers = 4;

        TestResult testResult = new TestResult(countRightAnswers, countPersonRightAnswers, person);

        String expectedMessage = "Congratulations, Jhon Dou! You passed the test. Number of correct answers = 4!";
        String actualMessage = testResult.getResult();

        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("is checking for fail test.")
    @Test
    void checkingFailTest() {
        int countRightAnswers = 5;
        int countPersonRightAnswers = 4;

        TestResult testResult = new TestResult(countRightAnswers, countPersonRightAnswers, person);

        String expectedMessage = "Sorry, Jhon Dou! You failed the test. Number of correct answers = 4!";
        String actualMessage = testResult.getResult();

        assertEquals(expectedMessage, actualMessage);
    }
}
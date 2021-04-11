package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.TestResult;
import ru.otus.mezgin.testdata.PersonData;
import ru.otus.mezgin.testdata.QuestionData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The CheckAnswersServiceImplTest class")
class CheckAnswersServiceImplTest {

    private final List<Question> questionList = QuestionData.getListQuestions();

    private final CheckAnswersService checkAnswersService = new CheckAnswersServiceImpl(3);

    @DisplayName("is checking the correctness of the all answers.")
    @Test
    void checkCorrectAnswers() {
        String expectedResult = new TestResult(3, 3, PersonData.getPersonWithoutAnswers()).getResult();

        TestResult testResult = checkAnswersService.checkAnswers(PersonData.getPersonWithRightAnswer(), questionList);

        assertEquals(expectedResult, testResult.getResult());
    }

    @DisplayName("is checking if the all answers are not correct.")
    @Test
    void checkNotCorrectAnswers() {
        String expectedResult = new TestResult(3, 0, PersonData.getPersonWithoutAnswers()).getResult();

        TestResult testResult = checkAnswersService.checkAnswers(PersonData.getPersonWithWrongAnswer(), questionList);

        assertEquals(expectedResult, testResult.getResult());
    }
}
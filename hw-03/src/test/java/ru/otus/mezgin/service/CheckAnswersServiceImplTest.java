package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.TestResult;
import ru.otus.mezgin.testdata.AnswerData;
import ru.otus.mezgin.testdata.QuestionData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("The CheckAnswersServiceImplTest class")
class CheckAnswersServiceImplTest {

    private final static int COUNT_PERSON_RIGHT_ANSWERS = 3;

    private final static int COUNT_PERSON_RIGHT_ANSWERS_ZERO = 0;

    private final List<Question> questionList = QuestionData.getListQuestions();

    private final CheckAnswersService checkAnswersService = new CheckAnswersServiceImpl();

    @DisplayName("is checking the correctness of the all answers.")
    @Test
    void checkCorrectAnswers() {
        TestResult testResult = new TestResult(AnswerData.getDataWithRightAnswer());

        int actualResult = checkAnswersService.checkAnswers(testResult, questionList);

        assertEquals(COUNT_PERSON_RIGHT_ANSWERS, actualResult);
    }

    @DisplayName("is checking if the all answers are not correct.")
    @Test
    void checkNotCorrectAnswers() {
        TestResult testResult = new TestResult(AnswerData.getDataWithWrongAnswer());

        int actualResult = checkAnswersService.checkAnswers(testResult, questionList);

        assertEquals(COUNT_PERSON_RIGHT_ANSWERS_ZERO, actualResult);
    }
}
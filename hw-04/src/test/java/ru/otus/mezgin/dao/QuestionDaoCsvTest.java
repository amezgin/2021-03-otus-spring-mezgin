package ru.otus.mezgin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.QuestionsFindException;
import ru.otus.mezgin.service.QuestionFileNameProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

@DisplayName("The Person class")
@SpringBootTest
class QuestionDaoCsvTest {

    @MockBean
    private QuestionFileNameProvider questionFileNameProvider;

    @Autowired
    private QuestionDao questionDao;

    @DisplayName("is finding all questions.")
    @Test
    void findAllQuestions() throws QuestionsFindException {

        given(this.questionFileNameProvider.getQuestionFileName()).willReturn("questions.csv");

        for (Question question : questionDao.findAll()) {
            assertAll(
                    () -> assertThat(question.getCorrectAnswers()).isNotNull(),
                    () -> assertThat(question.getText()).isNotNull(),
                    () -> assertThat(question.getNumber()).isNotNull(),
                    () -> assertThat(question.getType()).isNotNull());
        }
    }

    @DisplayName("is checking exception's type for incorrect file.")
    @Test
    void checkingExceptionTypeIncorrectFile() {

        given(this.questionFileNameProvider.getQuestionFileName()).willReturn("incorrectfile.csv");

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDao::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }

    @DisplayName("is checking exception's type for the missing file.")
    @Test
    void checkingExceptionTypeMissingFile() {

        given(this.questionFileNameProvider.getQuestionFileName()).willReturn("question.csv");

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDao::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }
}
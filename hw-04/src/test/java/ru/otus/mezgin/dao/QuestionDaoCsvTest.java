package ru.otus.mezgin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.QuestionsFindException;
import ru.otus.mezgin.config.QuestionDaoCsvFileNameTestConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("The Person class")
@SpringBootTest
@Import(QuestionDaoCsvFileNameTestConfig.class)
class QuestionDaoCsvTest {

    @Autowired
    private QuestionDaoCsvFileNameTestConfig QuestionDaoCsvFileNameTestConfig;

    @DisplayName("is finding all questions.")
    @Test
    void findAllQuestions() throws QuestionsFindException {

        for (Question question : QuestionDaoCsvFileNameTestConfig.questionDaoCsvCorrectFile().findAll()) {
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

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, QuestionDaoCsvFileNameTestConfig.questionDaoCsvMissingFile()::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }

    @DisplayName("is checking exception's type for the missing file.")
    @Test
    void checkingExceptionTypeMissingFile() {

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, QuestionDaoCsvFileNameTestConfig.questionDaoCsvIncorrectFile()::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }
}
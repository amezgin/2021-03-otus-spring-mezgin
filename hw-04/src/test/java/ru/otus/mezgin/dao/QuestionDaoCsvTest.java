package ru.otus.mezgin.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.QuestionsFindException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("The Person class")
@SpringBootTest
class QuestionDaoCsvTest {

    @Autowired
    QuestionDaoCsv questionDaoCsv;

    @DisplayName("is finding all questions.")
    @Test
    void findAllQuestions() throws QuestionsFindException {

        for (Question question : questionDaoCsv.findAll()) {
            assertAll(
                    () -> assertThat(question.getCorrectAnswers()).isNotNull(),
                    () -> assertThat(question.getText()).isNotNull(),
                    () -> assertThat(question.getNumber()).isNotNull(),
                    () -> assertThat(question.getType()).isNotNull());
        }
    }
}
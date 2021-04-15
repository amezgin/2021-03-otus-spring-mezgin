package ru.otus.mezgin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.errors.QuestionsFindException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("The Person class")
class QuestionDaoCsvTest {

    private final static int COUNT_QUESTIONS = 5;

    @DisplayName("is finding all questions.")
    @Test
    void findAllQuestions() throws QuestionsFindException {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/questions.csv");

        assertEquals(questionDaoCsv.findAll().size(), COUNT_QUESTIONS);
    }

    @DisplayName("is checking exception's type for the missing file.")
    @Test
    void checkingExceptionTypeMissingFile() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/question.csv");

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDaoCsv::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }

    @DisplayName("is checking exception's type for incorrect file.")
    @Test
    void checkingExceptionTypeIncorrectFile() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/incorrectfile.csv");

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDaoCsv::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }
}
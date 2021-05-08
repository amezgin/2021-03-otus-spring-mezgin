package ru.otus.mezgin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.otus.mezgin.errors.QuestionsFindException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("The Person class")
@SpringBootTest
@TestPropertySource(properties = {"quiz.file-name=incorrectfile.csv"})
class QuestionDaoCsvTestWithIncorrectFile {

    @Autowired
    QuestionDaoCsv questionDaoCsv;

    @DisplayName("is checking exception's type for incorrect file.")
    @Test
    void checkingExceptionTypeIncorrectFile() {

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDaoCsv::findAll);

        assertThat(exception).isInstanceOf(QuestionsFindException.class);
    }
}
package ru.otus.mezgin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.errors.QuestionsFindException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("The Person class")
class QuestionDaoCsvTest {

    @DisplayName("is finding all questions.")
    @Test
    void findAllQuestions() throws QuestionsFindException {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/questions.csv");

        System.out.println(questionDaoCsv.findAll().size());
    }

    @DisplayName("is checking for file exist.")
    @Test
    void checkingFileExist() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/question.csv");

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDaoCsv::findAll);

        String expectedMessage = "File reading error. File = ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("is checking for correct file.")
    @Test
    void checkingCorrectFile() {
        QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv("/incorrectfile.csv");

        Exception exception = Assertions.assertThrows(QuestionsFindException.class, questionDaoCsv::findAll);

        String expectedMessage = "The file contains invalid data. File = ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
package ru.otus.mezgin.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.otus.mezgin.dao.QuestionDaoCsv;

@TestConfiguration
public class QuestionDaoCsvFileNameTestConfig {

    @Bean
    public QuestionDaoCsv questionDaoCsvCorrectFile() {
        return new QuestionDaoCsv("questions.csv");
    }


    @Bean
    public QuestionDaoCsv questionDaoCsvMissingFile() {
        return new QuestionDaoCsv("question.csv");
    }

    @Bean
    public QuestionDaoCsv questionDaoCsvIncorrectFile() {
        return new QuestionDaoCsv("incorrectfile.csv");
    }
}

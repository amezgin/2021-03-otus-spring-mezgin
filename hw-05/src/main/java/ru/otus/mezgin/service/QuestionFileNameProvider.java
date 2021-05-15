package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuestionFileNameProvider {


    private final String questionFileName;

    public QuestionFileNameProvider(@Value("${quiz.file-name}") String questionFileName) {
        this.questionFileName = questionFileName;
    }

    public String getQuestionFileName() {
        return questionFileName;
    }
}

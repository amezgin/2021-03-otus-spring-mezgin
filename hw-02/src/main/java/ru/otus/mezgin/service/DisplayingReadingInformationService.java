package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.ReadInputLineException;

public interface DisplayingReadingInformationService {

    String readLine() throws ReadInputLineException;

    void printQuestion(Question question);

    void printWelcome();

    void printFullNameQuestion();

    void printContinue();

    void printResult(String result);

    void printExit();
}

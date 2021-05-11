package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.ReadInputLineException;

import java.util.List;

public interface AnswerService {

    int getCountPersonRightAnswers();

    List<Answer> getAnswers();

    void fillAnswers(Question question) throws ReadInputLineException;
}

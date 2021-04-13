package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.TestResult;

import java.util.List;

public interface CheckAnswersService {

    int checkAnswers(TestResult testResult, List<Question> questions) ;
}

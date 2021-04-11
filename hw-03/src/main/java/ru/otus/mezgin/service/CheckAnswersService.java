package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.TestResult;

import java.util.List;

public interface CheckAnswersService {

    TestResult checkAnswers(Person person, List<Question> questions);
}

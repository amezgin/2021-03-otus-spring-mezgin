package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.TestResult;
import ru.otus.mezgin.errors.QuestionsFindException;
import ru.otus.mezgin.errors.ReadInputLineException;

public interface QuestionService {

    TestResult askQuestion(Person person) throws QuestionsFindException, ReadInputLineException;
}

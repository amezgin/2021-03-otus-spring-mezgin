package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;

import java.util.List;

public interface CheckAnswersService {

    String checkAnswers(Person person, List<Question> questions);
}

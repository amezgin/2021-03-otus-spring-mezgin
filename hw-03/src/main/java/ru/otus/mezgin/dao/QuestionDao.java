package ru.otus.mezgin.dao;

import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.QuestionsFindException;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll() throws QuestionsFindException;
}

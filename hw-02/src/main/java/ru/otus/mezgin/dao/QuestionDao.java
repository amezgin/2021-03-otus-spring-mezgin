package ru.otus.mezgin.dao;

import ru.otus.mezgin.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll() throws Exception;
}

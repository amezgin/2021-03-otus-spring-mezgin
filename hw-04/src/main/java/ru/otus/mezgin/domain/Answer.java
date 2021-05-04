package ru.otus.mezgin.domain;

import ru.otus.mezgin.domain.enums.QuestionType;

public class Answer extends Item {

    private String personAnswer;

    public Answer() {
    }

    public Answer(int number, QuestionType type, String personAnswer) {
        this.setNumber(number);
        this.setType(type);
        this.personAnswer = personAnswer;
    }

    public String getPersonAnswer() {
        return personAnswer;
    }

    public void setPersonAnswer(String personAnswer) {
        this.personAnswer = personAnswer;
    }
}

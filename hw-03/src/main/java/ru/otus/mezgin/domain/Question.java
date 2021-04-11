package ru.otus.mezgin.domain;

import ru.otus.mezgin.domain.enums.QuestionType;

public class Question extends Item {

    private String text;

    private String correctAnswers;

    public Question() {
    }

    public Question(int number, QuestionType type, String text, String correctAnswers) {
        this.setNumber(number);
        this.setType(type);
        this.text = text;
        this.correctAnswers = correctAnswers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}

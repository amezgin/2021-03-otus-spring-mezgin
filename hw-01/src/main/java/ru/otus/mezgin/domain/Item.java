package ru.otus.mezgin.domain;

import ru.otus.mezgin.domain.enums.QuestionType;

public abstract class Item {

    private int number;

    private QuestionType type;

    public int geNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}

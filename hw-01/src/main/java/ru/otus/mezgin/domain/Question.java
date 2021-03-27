package ru.otus.mezgin.domain;

public class Question extends Item {

    private String text;

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

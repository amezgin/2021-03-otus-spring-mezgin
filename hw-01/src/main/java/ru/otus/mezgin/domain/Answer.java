package ru.otus.mezgin.domain;

import java.util.List;

public class Answer extends Item {

    private List<String> answers;

    public Answer() {
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}

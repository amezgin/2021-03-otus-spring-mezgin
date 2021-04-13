package ru.otus.mezgin.domain;

import java.util.List;

public class TestResult {

    private List<Answer> answers;

    public TestResult(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

package ru.otus.mezgin.domain;

import java.util.LinkedList;
import java.util.List;

public class Person {

    private final String name;

    private final String lastName;

    private List<Answer> answers = new LinkedList<>();

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

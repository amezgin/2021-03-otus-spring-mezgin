package ru.otus.mezgin.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) && lastName.equals(person.lastName) && answers.equals(person.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, answers);
    }
}

package ru.otus.mezgin.domain;

public class TestResult {

    private final int countRightAnswers;

    private final int countPersonRightAnswers;

    private final String personName;

    private final String personLastName;

    public TestResult(int countRightAnswers, int countPersonRightAnswers, String personName, String personLastName) {
        this.countRightAnswers = countRightAnswers;
        this.countPersonRightAnswers = countPersonRightAnswers;
        this.personName = personName;
        this.personLastName = personLastName;
    }

    public int getCountRightAnswers() {
        return countRightAnswers;
    }

    public int getCountPersonRightAnswers() {
        return countPersonRightAnswers;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }
}

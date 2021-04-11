package ru.otus.mezgin.domain;

public class TestResult {

    private final int countRightAnswers;

    private final int countPersonRightAnswers;

    private final Person person;

    public TestResult(int countRightAnswers, int countPersonRightAnswers, Person person) {
        this.countRightAnswers = countRightAnswers;
        this.countPersonRightAnswers = countPersonRightAnswers;
        this.person = person;
    }

    public String getResult() {
        String result = String.format("Sorry, %s %s! You failed the test. Number of correct answers = %s!", person.getName(), person.getLastName(), countPersonRightAnswers);

        if (countPersonRightAnswers >= countRightAnswers) {
            result = String.format("Congratulations, %s %s! You passed the test. Number of correct answers = %s!", person.getName(), person.getLastName(), countPersonRightAnswers);
        }

        return result;
    }
}

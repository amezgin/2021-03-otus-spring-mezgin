package ru.otus.mezgin.testdata;

import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class PersonData {

    public final static String PERSON_NAME = "Jhon";

    public final static String PERSON_LASTNAME = "Dou";

    private final static String PERSON_ANSWER_1 = "c";

    private final static String PERSON_ANSWER_2 = "c, a, b";

    private final static String PERSON_ANSWER_3 = "Mars";

    private final static String PERSON_WRONG_ANSWER_1 = "b";

    private final static String PERSON_WRONG_ANSWER_2 = "a, b";

    private final static String PERSON_WRONG_ANSWER_3 = "Pluto";

    public static Person getPersonWithRightAnswer() {
        Person person = new Person(PERSON_NAME, PERSON_LASTNAME);
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer(1, QuestionType.SINGLE, PERSON_ANSWER_1);
        Answer answer2 = new Answer(2, QuestionType.MULTI, PERSON_ANSWER_2);
        Answer answer3 = new Answer(3, QuestionType.EXTENDED, PERSON_ANSWER_3);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        person.setAnswers(answers);
        return person;
    }

    public static Person getPersonWithWrongAnswer() {
        Person person = new Person(PERSON_NAME, PERSON_LASTNAME);
        List<Answer> answers = new ArrayList<>();
        Answer answer1 = new Answer(1, QuestionType.SINGLE, PERSON_WRONG_ANSWER_1);
        Answer answer2 = new Answer(2, QuestionType.MULTI, PERSON_WRONG_ANSWER_2);
        Answer answer3 = new Answer(3, QuestionType.EXTENDED, PERSON_WRONG_ANSWER_3);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        person.setAnswers(answers);
        return person;
    }

    public static Person getPersonWithoutAnswers() {
        return new Person(PERSON_NAME, PERSON_LASTNAME);
    }
}

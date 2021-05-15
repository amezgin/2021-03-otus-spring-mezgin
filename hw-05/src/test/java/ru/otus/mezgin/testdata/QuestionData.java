package ru.otus.mezgin.testdata;

import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {

    public final static String QUESTION_1 = "How many planets are there in the solar system?";

    public final static String QUESTION_2 = "Select the 3 planets closest to the Sun.";

    public final static String QUESTION_3 = "Write the name your favorite planet.";

    public final static String RIGHT_ANSWER_1 = "c";

    public final static String RIGHT_ANSWER_2 = "a, b, c";

    public final static String RIGHT_ANSWER_3 = "Mercury, Venus, Earth, Mars, Saturn, Uranus, Neptune, Jupiter";

    public static List<Question> getListQuestions() {
        List<Question> questionList = new ArrayList<>();
        Question question1 = new Question(1, QuestionType.SINGLE, QUESTION_1, RIGHT_ANSWER_1);
        Question question2 = new Question(2, QuestionType.MULTI, QUESTION_2, RIGHT_ANSWER_2);
        Question question3 = new Question(3, QuestionType.EXTENDED, QUESTION_3, RIGHT_ANSWER_3);
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        return questionList;
    }
}

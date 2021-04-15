package ru.otus.mezgin.util;

public class CreateResultInfoUtil {

    public static String createResultInfo(int countRightAnswers, int countPersonRightAnswers, String personName,
                                          String personLastName) {
        String result = String.format("Sorry, %s %s! You failed the test. Number of correct answers = %s!", personName, personLastName, countPersonRightAnswers);

        if (countPersonRightAnswers >= countRightAnswers) {
            result = String.format("Congratulations, %s %s! You passed the test. Number of correct answers = %s!", personName, personLastName, countPersonRightAnswers);
        }

        return result;
    }
}

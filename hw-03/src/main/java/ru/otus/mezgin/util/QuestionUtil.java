package ru.otus.mezgin.util;

import ru.otus.mezgin.domain.Question;

public class QuestionUtil {

    public static String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder();
        switch (question.getType()) {
            case SINGLE: {
                builder.append("\nChoose 1 correct answer.");
                break;
            }
            case MULTI: {
                builder.append("\nChoose some correct answers. Enter the answer separated by commas.");
                break;
            }
            default: {
                builder.append("\nWrite the answer.");
            }
        }
        builder.append("\nQ: ");
        builder.append(question.getText());
        builder.append("\nYour answer: ");
        return builder.toString();
    }
}

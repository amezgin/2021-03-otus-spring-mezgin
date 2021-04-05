package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CheckAnswersServiceImpl implements CheckAnswersService {

    @Value("${questions.mincorrectansw}")
    private int countRightAnswers;

    @Override
    public String checkAnswers(Person person, List<Question> questions) {

        String result;
        int countRight = 0;

        for (Answer answer : person.getAnswers()) {
            Optional<Question> question = questions.stream().filter(q -> q.getNumber() == answer.getNumber()).findAny();
            if (question.isPresent()) {
                switch (answer.getType()) {
                    case SINGLE: {
                        if (answer.getPersonAnswer().equals(question.get().getCorrectAnswers())) {
                            countRight++;
                        }
                        break;
                    }
                    case MULTI: {
                        if (checkMultiAnswer(question.get(), answer)) {
                            countRight++;
                        }
                        break;
                    }
                    default: {
                        if (checkExtendedAnswer(question.get(), answer)) {
                            countRight++;
                        }
                    }
                }
            }
        }
        if (countRight >= countRightAnswers) {
            result = String.format("Congratulations, %s %s! You passed the test. Number of correct answers = %s", person.getName(), person.getLastName(), countRight);
        } else {
            result = String.format("Sorry, %s %s! You failed the test. Number of correct answers = %s", person.getName(), person.getLastName(), countRight);
        }
        return result;
    }

    private boolean checkMultiAnswer(Question question, Answer answer) {
        String[] questions = question.getCorrectAnswers().replaceAll(" ", "").split(",");
        Arrays.sort(questions);
        String[] answers = answer.getPersonAnswer().replaceAll(" ", "").split(",");
        Arrays.sort(answers);
        return Arrays.equals(questions, answers);
    }

    private boolean checkExtendedAnswer(Question question, Answer answer) {
        String[] questions = question.getCorrectAnswers().replaceAll(" ", "").split(",");
        String answ = answer.getPersonAnswer().replaceAll("[^A-Za-z]+","");
        return Arrays.stream(questions).anyMatch(p -> (answ.length() > 3 && p.equals(answ)));
    }
}

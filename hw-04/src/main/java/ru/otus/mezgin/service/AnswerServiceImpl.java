package ru.otus.mezgin.service;

import org.springframework.stereotype.Service;
import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.ReadInputLineException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private int countPersonRightAnswers = 0;

    private final InOutService inOutService;

    private final List<Answer> answers = new ArrayList<>();

    public AnswerServiceImpl(InOutService inOutService) {
        this.inOutService = inOutService;
    }

    @Override
    public int getCountPersonRightAnswers() {
        return countPersonRightAnswers;
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public void fillAnswers(Question question) throws ReadInputLineException {
        Answer personAnswer = new Answer();
        String answer = inOutService.readLine().trim();
        personAnswer.setNumber(question.getNumber());
        personAnswer.setType(question.getType());
        personAnswer.setPersonAnswer(answer.toLowerCase());
        answers.add(personAnswer);
        checkAnswers(personAnswer, question);
    }

    private void checkAnswers(Answer answer, Question question) {
        switch (answer.getType()) {
            case SINGLE: {
                if (answer.getPersonAnswer().equalsIgnoreCase(question.getCorrectAnswers())) {
                    countPersonRightAnswers++;
                }
                break;
            }
            case MULTI: {
                if (checkMultiAnswer(question, answer)) {
                    countPersonRightAnswers++;
                }
                break;
            }
            default: {
                if (checkExtendedAnswer(question, answer)) {
                    countPersonRightAnswers++;
                }
            }
        }
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
        String answ = answer.getPersonAnswer().replaceAll("[^A-Za-z]+", "");
        return Arrays.stream(questions).anyMatch(p -> (answ.length() > 3 && p.equals(answ)));
    }
}

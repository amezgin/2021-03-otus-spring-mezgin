package ru.otus.mezgin.service;

import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.dao.QuestionDao;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.TestResult;
import ru.otus.mezgin.errors.QuestionsFindException;
import ru.otus.mezgin.errors.ReadInputLineException;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    private final AnswerService answerService;

    private final InOutLocalizationWrapper inOutLocalizationWrapper;

    private final QuizConfig quizConfig;

    private final String separator = System.lineSeparator();

    public QuestionServiceImpl(QuestionDao questionDao,
                               AnswerService answerService, InOutLocalizationWrapper inOutLocalizationWrapper, QuizConfig quizConfig) {
        this.questionDao = questionDao;
        this.answerService = answerService;
        this.inOutLocalizationWrapper = inOutLocalizationWrapper;
        this.quizConfig = quizConfig;
    }

    @Override
    public TestResult askQuestion(Person person) throws QuestionsFindException, ReadInputLineException {
        List<Question> questions = questionDao.findAll();
        for (Question question : questions) {
            inOutLocalizationWrapper.printDefault(formatQuestion(question));
            answerService.fillAnswers(question);
        }
        return new TestResult(quizConfig.getMinimumNumberCorrectAnswers(), answerService.getCountPersonRightAnswers(),
                person.getName(), person.getLastName());
    }

    private String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder(separator);
        builder.append(inOutLocalizationWrapper.getLocalizedString("question." + question.getType().getName().toLowerCase()));
        builder.append(separator);
        builder.append(inOutLocalizationWrapper.getLocalizedString("question.q"));
        builder.append(question.getText());
        builder.append(separator);
        builder.append(inOutLocalizationWrapper.getLocalizedString("your.answer"));
        return builder.toString();
    }
}

package ru.otus.mezgin.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.dao.QuestionDao;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.TestResult;
import ru.otus.mezgin.domain.enums.QuestionType;
import ru.otus.mezgin.errors.QuestionsFindException;
import ru.otus.mezgin.errors.ReadInputLineException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.mezgin.domain.enums.QuestionType.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao repository;

    private final AnswerService answerService;

    private final InOutService inOutService;

    private final MessageSource messageSource;

    private final QuizConfig quizConfig;

    private final String separator = System.lineSeparator();

    private final Map<QuestionType, String> questionTypeStringMap = new HashMap<>();

    public QuestionServiceImpl(QuestionDao repository,
                               AnswerService answerService, InOutService inOutService, MessageSource messageSource, QuizConfig quizConfig) {
        this.repository = repository;
        this.answerService = answerService;
        this.inOutService = inOutService;
        this.messageSource = messageSource;
        this.quizConfig = quizConfig;
        fillQuestionTypeStringMap();
    }

    @Override
    public TestResult askQuestion(Person person) throws QuestionsFindException, ReadInputLineException {
        List<Question> questions = repository.findAll();
        for (Question question : questions) {
            inOutService.print(formatQuestion(question));
            answerService.fillAnswers(question);
        }
        return new TestResult(quizConfig.getMincorrectansw(), answerService.getCountPersonRightAnswers(),
                person.getName(), person.getLastName());
    }

    private String formatQuestion(Question question) {
        StringBuilder builder = new StringBuilder(separator);
        builder.append(questionTypeStringMap.get(question.getType()));
        builder.append(separator);
        builder.append(messageSource.getMessage("question.q", null, quizConfig.getQuizLocale()));
        builder.append(question.getText());
        builder.append(separator);
        builder.append(messageSource.getMessage("your.answer", null, quizConfig.getQuizLocale()));
        return builder.toString();
    }

    private void fillQuestionTypeStringMap() {
        String singleQuestion = messageSource.getMessage("question.single", null, quizConfig.getQuizLocale());
        String multiQuestion = messageSource.getMessage("question.multi", null, quizConfig.getQuizLocale());
        String extendedQuestion = messageSource.getMessage("question.extended", null, quizConfig.getQuizLocale());
        String defaultQuestion = messageSource.getMessage("question.default", null, quizConfig.getQuizLocale());
        questionTypeStringMap.put(SINGLE, singleQuestion);
        questionTypeStringMap.put(MULTI, multiQuestion);
        questionTypeStringMap.put(EXTENDED, extendedQuestion);
        questionTypeStringMap.put(DEFAULT, defaultQuestion);
    }
}

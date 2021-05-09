package ru.otus.mezgin.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.domain.TestResult;

@Service
public class TestResultServiceImpl implements TestResultService {

    private final InOutService inOutService;

    private final MessageSource messageSource;

    private final QuizConfig quizConfig;

    public TestResultServiceImpl(InOutService inOutService, MessageSource messageSource, QuizConfig quizConfig) {
        this.inOutService = inOutService;
        this.messageSource = messageSource;
        this.quizConfig = quizConfig;
    }

    @Override
    public void printTestResult(TestResult testResult) {
        String result = String.format(messageSource.getMessage("result.fail",
                new String[]{testResult.getPersonName(), testResult.getPersonLastName(), String.valueOf(testResult.getCountPersonRightAnswers())},
                quizConfig.getQuizLocale()));

        if (testResult.getCountPersonRightAnswers() >= testResult.getCountRightAnswers()) {
            result = String.format(messageSource.getMessage("result.pass",
                    new String[]{testResult.getPersonName(), testResult.getPersonLastName(), String.valueOf(testResult.getCountPersonRightAnswers())},
                    quizConfig.getQuizLocale()));
        }

        inOutService.println("");
        inOutService.println(result);
    }
}

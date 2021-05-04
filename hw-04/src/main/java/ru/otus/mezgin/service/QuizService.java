package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.errors.ReadInputLineException;

@Service
public class QuizService {

    private final QuestionService questionService;

    private final PersonService personService;

    private final TestResultService testResultService;

    private final InOutService inOutService;

    private final MessageSource messageSource;

    private final QuizConfig quizConfig;

    @Autowired
    public QuizService(QuestionService questionService, PersonService personService, TestResultService testResultService, InOutService inOutService, MessageSource messageSource, QuizConfig quizConfig) {
        this.questionService = questionService;
        this.personService = personService;
        this.testResultService = testResultService;
        this.inOutService = inOutService;
        this.messageSource = messageSource;
        this.quizConfig = quizConfig;
    }

    public void runQuiz() {
        try {
            printWelcome();
            Person person = personService.createPerson();
            printContinue();
            String answer = inOutService.readLine();
            if ("n".equals(answer) || "н".equals(answer)) {
                return;
            }

            testResultService.printTestResult(questionService.askQuestion(person));
            printExit();
        } catch (Exception e) {
            inOutService.println(e.getMessage());
        }
    }

    private void printWelcome() {
        inOutService.println(messageSource.getMessage("quiz.header", null, quizConfig.getQuizLocale()));
        inOutService.println(messageSource.getMessage("quiz.welcome", null, quizConfig.getQuizLocale()));
    }

    private void printContinue() {
        inOutService.println(messageSource.getMessage("quiz.continue", null, quizConfig.getQuizLocale()));
        inOutService.print(messageSource.getMessage("your.answer", null, quizConfig.getQuizLocale()));
    }

    private void printExit() {
        inOutService.println(messageSource.getMessage("quiz.bye", null, quizConfig.getQuizLocale()));
    }
}

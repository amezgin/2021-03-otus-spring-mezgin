package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.domain.Person;

@Service
public class QuizService {

    private final QuestionService questionService;

    private final PersonService personService;

    private final TestResultService testResultService;

    private final InOutLocalizationWrapper inOutLocalizationWrapper;

    @Autowired
    public QuizService(QuestionService questionService, PersonService personService, TestResultService testResultService, InOutService inOutService, MessageSource messageSource, QuizConfig quizConfig, InOutLocalizationWrapper inOutLocalizationWrapper) {
        this.questionService = questionService;
        this.personService = personService;
        this.testResultService = testResultService;
        this.inOutLocalizationWrapper = inOutLocalizationWrapper;
    }

    public void runQuiz() {
        try {
            printWelcome();
            Person person = personService.createPerson();
            printContinue();
            String answer = inOutLocalizationWrapper.readLine();
            if ("n".equals(answer) || "н".equals(answer)) {
                return;
            }

            testResultService.printTestResult(questionService.askQuestion(person));
            printExit();
        } catch (Exception e) {
            inOutLocalizationWrapper.printDefault(e.getMessage());
        }
    }

    private void printWelcome() {
        inOutLocalizationWrapper.println("quiz.header");
        inOutLocalizationWrapper.println("quiz.welcome");
    }

    private void printContinue() {
        inOutLocalizationWrapper.println("quiz.continue");
        inOutLocalizationWrapper.print("your.answer");
    }

    private void printExit() {
        inOutLocalizationWrapper.println("quiz.bye");
    }
}

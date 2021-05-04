package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.errors.ReadInputLineException;

@Service
public class PersonServiceImpl implements PersonService {

    private final InOutService inOutService;

    private final MessageSource messageSource;

    private final QuizConfig quizConfig;

    @Autowired
    public PersonServiceImpl(InOutService inOutService, MessageSource messageSource, QuizConfig quizConfig) {
        this.inOutService = inOutService;
        this.messageSource = messageSource;
        this.quizConfig = quizConfig;
    }

    @Override
    public Person createPerson() throws ReadInputLineException {
        askFullName();
        String fullName = inOutService.readLine().trim();

        while (fullName.contains("  ")) {
            fullName = fullName.replaceAll(" {2}", " ");
        }

        String[] fullNameArr = fullName.split(" ");

        Person person;
        if (fullNameArr.length == 0) {
            person = new Person("Anonim", "Anonim");
        } else if (fullNameArr.length == 1) {
            person = new Person(fullNameArr[0], "");
        } else {
            person = new Person(fullNameArr[0], fullNameArr[1]);
        }
        return person;
    }

    private void askFullName() {
        inOutService.println(messageSource.getMessage("your.name", null, quizConfig.getQuizLocale()));
        inOutService.print(messageSource.getMessage("your.answer", null, quizConfig.getQuizLocale()));
    }
}

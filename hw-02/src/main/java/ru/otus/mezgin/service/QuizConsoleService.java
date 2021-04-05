package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.dao.QuestionDao;
import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizConsoleService {

    private final QuestionDao repository;

    private final DisplayingReadingInformationService displayingReadingInformationService;

    private final CheckAnswersService checkAnswersService;

    @Autowired
    public QuizConsoleService(QuestionDao repository, DisplayingReadingInformationService displayingReadingInformationService, CheckAnswersService checkAnswersService) {
        this.repository = repository;
        this.displayingReadingInformationService = displayingReadingInformationService;
        this.checkAnswersService = checkAnswersService;
    }

    public void runQuiz() {
        try {
            List<Question> questions = repository.findAll();
            List<Answer> answers = new ArrayList<>();
            displayingReadingInformationService.printWelcome();
            String answer = displayingReadingInformationService.readLine();
            if (!"n".equalsIgnoreCase(answer)) {
                displayingReadingInformationService.printFullNameQuestion();
                answer = displayingReadingInformationService.readLine();
                Person person = createPerson(answer.trim());

                for (Question question : questions) {
                    Answer personAnswer = new Answer();
                    displayingReadingInformationService.printQuestion(question);
                    answer = displayingReadingInformationService.readLine().trim();
                    personAnswer.setNumber(question.getNumber());
                    personAnswer.setType(question.getType());
                    personAnswer.setPersonAnswer(answer.toLowerCase());
                    answers.add(personAnswer);
                    displayingReadingInformationService.printContinue();
                    if ("n".equalsIgnoreCase(displayingReadingInformationService.readLine())) {
                        break;
                    }
                }
                person.setAnswers(answers);
                displayingReadingInformationService.printResult(checkAnswersService.checkAnswers(person, questions));
            }
            displayingReadingInformationService.printExit();
        } catch (Exception e) {
            displayingReadingInformationService.printResult(e.getMessage());
        }
    }

    private Person createPerson(String fullName) {
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
}

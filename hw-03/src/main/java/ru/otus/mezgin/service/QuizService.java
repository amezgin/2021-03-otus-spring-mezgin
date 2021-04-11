package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.dao.QuestionDao;
import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Person;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.ReadInputLineException;
import ru.otus.mezgin.util.QuestionUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final QuestionDao repository;
    private final CheckAnswersService checkAnswersService;
    private final PersonService personService;
    private final InOutService inOutService;

    @Autowired
    public QuizService(QuestionDao repository, PersonService personService,
                       CheckAnswersService checkAnswersService, InOutService inOutService) {
        this.repository = repository;
        this.personService = personService;
        this.checkAnswersService = checkAnswersService;
        this.inOutService = inOutService;
    }

    public void runQuiz() {
        try {
            List<Question> questions = repository.findAll();
            List<Answer> answers = new ArrayList<>();
            printWelcome();
            String answer = inOutService.readLine();
            if (!"n".equalsIgnoreCase(answer)) {
                personService.askFullName();
                answer = inOutService.readLine();
                Person person = personService.createPerson(answer.trim());

                for (Question question : questions) {
                    inOutService.print(QuestionUtil.formatQuestion(question));
                    fillAnswers(answers, question);
                    printContinue();
                    if ("n".equalsIgnoreCase(inOutService.readLine())) {
                        break;
                    }
                }
                person.setAnswers(answers);
                inOutService.println(checkAnswersService.checkAnswers(person, questions).getResult());
            }
            printExit();
        } catch (Exception e) {
            inOutService.println(e.getMessage());
        }
    }

    private void fillAnswers(List<Answer> answers, Question question) throws ReadInputLineException {
        Answer personAnswer = new Answer();
        String answer = inOutService.readLine().trim();
        personAnswer.setNumber(question.getNumber());
        personAnswer.setType(question.getType());
        personAnswer.setPersonAnswer(answer.toLowerCase());
        answers.add(personAnswer);
    }

    private void printWelcome() {
        inOutService.println(" ********************************** QUIZ ********************************* ");
        inOutService.println("Hi! You have started the questionnaire. We'll ask you a few questions. Please answer honestly!");
        inOutService.println("Are you ready to start? If not, enter \"n\".");
        inOutService.print("Your answer: ");
    }

    private void printContinue() {
        inOutService.println("\nContinue? If not, enter \"n\".");
        inOutService.print("Your answer: ");
    }

    private void printExit() {
        inOutService.println("Bye!");
    }
}

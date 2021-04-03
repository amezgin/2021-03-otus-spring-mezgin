package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.errors.ReadInputLineException;

@Service
public class DisplayingReadingInformationImpl implements DisplayingReadingInformationService {

    private final InOutService inOutService;

    @Autowired
    public DisplayingReadingInformationImpl(InOutService inOutService) {
        this.inOutService = inOutService;
    }

    @Override
    public String readLine() throws ReadInputLineException {
        return inOutService.readLine();
    }

    @Override
    public void printQuestion(Question question) {
        switch (question.getType()) {
            case SINGLE: {
                inOutService.println("\nChoose 1 correct answer.");
                break;
            }
            case MULTI: {
                inOutService.println("\nChoose some correct answers. Enter the answer separated by commas.");
                break;
            }
            default: {
                inOutService.println("\nWrite the answer.");
            }
        }
        inOutService.println("Q: " + question.getText());
        inOutService.print("Your answer: ");
    }

    @Override
    public void printWelcome() {
        inOutService.println(" ********************************** QUIZ ********************************* ");
        inOutService.println("Hi! You have started the questionnaire. We'll ask you a few questions. Please answer honestly!");
        inOutService.println("Are you ready to start? If not, enter \"n\".");
        inOutService.print("Your answer: ");
    }

    @Override
    public void printFullNameQuestion() {
        inOutService.println("What is your name? Write your full name, please. For example, Alexander Mezgin.");
        inOutService.print("Your answer: ");
    }

    @Override
    public void printContinue() {
        inOutService.println("Continue? If not, enter \"n\".");
    }

    @Override
    public void printResult(String result) {
        inOutService.println(result);
    }

    @Override
    public void printExit() {
        inOutService.println("Bye!");
    }
}

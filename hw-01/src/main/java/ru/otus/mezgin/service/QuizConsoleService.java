package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Question;

import java.util.List;
import java.util.Scanner;

public class QuizConsoleService {

    private boolean isStop = true;

    CsvReaderService csvReaderService;

    private List<Question> questions;

    private List<Answer> answers;

    public QuizConsoleService(CsvReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
        this.questions = this.csvReaderService.getQuestions();
        this.answers = this.csvReaderService.getAnswers();
    }

    public void runQuiz() throws Exception {
        this.csvReaderService.readCsv();
        try (Scanner scanner = new Scanner(System.in)) {
            this.printWelcome();
            int number = 0;
            do {
                if (scanner.nextLine().equalsIgnoreCase("n")) {
                    this.isStop = false;
                    this.printExit();
                    continue;
                }

                if (number < questions.size()) {
                    this.printQuestion(questions.get(number));
                    number++;
                } else {
                    this.isStop = false;
                    this.printBye();
                }
            } while (isStop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printWelcome() {
        System.out.println(" ********************************** QUIZ ********************************* ");
        System.out.println("Hi! You have started the questionnaire. We'll ask you a few questions. Please answer honestly!");
        System.out.println("Are you ready to start? If not, enter \"n\".");
        System.out.print("Your answer: ");
    }

    private void printQuestion(Question question) {
        System.out.println("\n\nTo abort the test, enter \"n\".");
        switch (question.getType()) {
            case SINGLE: {
                System.out.println("\nChoose 1 correct answer.");
                break;
            }
            case MULTY: {
                System.out.println("\nChoose some correct answers. Enter the answer separated by commas.");
                break;
            }
            default: {
                System.out.println("\nWrite the answer.");
            }
        }
        System.out.println("Q: " + question.getText().replace("\n", "\n"));
        System.out.print("Your answer: ");
    }

    private void printContinue() {
        System.out.println("Continue? If yes, enter \"y\". If not, enter \"n\".");
    }

    private void printBye() {
        System.out.println("You have answered all the questions. The result will be reported to you.");
    }

    private void printExit() {
        System.out.println("Bye!");
    }
}

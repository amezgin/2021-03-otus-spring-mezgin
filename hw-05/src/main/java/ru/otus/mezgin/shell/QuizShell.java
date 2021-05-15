package ru.otus.mezgin.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.mezgin.service.QuizService;

@ShellComponent
public class QuizShell {

    private final QuizService quizService;

    public QuizShell(QuizService quizService) {
        this.quizService = quizService;
    }

    @ShellMethod(key = "start", value = "Welcome to Quiz!")
    public void startQuiz() {
        quizService.runQuiz();
    }
}

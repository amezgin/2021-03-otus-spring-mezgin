package ru.otus.mezgin;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.mezgin.service.QuizConsoleService;

public class Main {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuizConsoleService service = context.getBean(QuizConsoleService.class);

        service.runQuiz();
    }
}

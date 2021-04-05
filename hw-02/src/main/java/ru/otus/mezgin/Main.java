package ru.otus.mezgin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.mezgin.service.QuizConsoleService;

@Configuration
@ComponentScan
@PropertySource(value="classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        QuizConsoleService service = context.getBean(QuizConsoleService.class);

        service.runQuiz();
    }
}

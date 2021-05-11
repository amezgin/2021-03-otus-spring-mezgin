package ru.otus.mezgin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.mezgin.service.QuizService;

@Configuration
@ComponentScan
@PropertySource(value="classpath:application.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        QuizService service = context.getBean(QuizService.class);

        service.runQuiz();
    }
}

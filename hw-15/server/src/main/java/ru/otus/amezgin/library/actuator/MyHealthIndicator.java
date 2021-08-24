package ru.otus.amezgin.library.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.service.AuthorService;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MyHealthIndicator implements HealthIndicator {

    private final AuthorService authorService;

    @Override
    public Health health() {
        List<Author> authors = authorService.getAll();
        if (authors.isEmpty()) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Внимание, нет доступных авторов!")
                    .build();
        } else {
            return Health.up().withDetail("message", "Все отлично, авторы присутствуют!").build();
        }
    }
}

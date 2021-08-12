package ru.otus.amezgin.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.amezgin.restclient.dto.BookDto;
import ru.otus.amezgin.restclient.exception.ExternalAuthorizationException;
import ru.otus.amezgin.restclient.service.BookService;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ExternalAuthorizationException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		BookService service = ctx.getBean(BookService.class);

		BookDto bookDto = service.getBook(1L);

		log.info("BookDto = {}", bookDto);
	}
}

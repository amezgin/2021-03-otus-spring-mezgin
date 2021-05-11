package ru.otus.mezgin.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.config.QuizConfig;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    private final QuizConfig quizConfig;

    public LocalizationServiceImpl(MessageSource messageSource, QuizConfig quizConfig) {
        this.messageSource = messageSource;
        this.quizConfig = quizConfig;
    }

    public String getLocalizedString(String var, String[] args) {
        return messageSource.getMessage(var, args, quizConfig.getQuizLocale());
    }
}

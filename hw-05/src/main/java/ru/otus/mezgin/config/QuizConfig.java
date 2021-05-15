package ru.otus.mezgin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "quiz")
public class QuizConfig {

    private Locale quizLocale;

    private String fileName;

    private int minimumNumberCorrectAnswers;

    public void setLocaleName(String localeName) {
        quizLocale = Locale.forLanguageTag(localeName);
    }

    public Locale getQuizLocale() {
        return quizLocale;
    }

    public void setQuizLocale(Locale quizLocale) {
        this.quizLocale = quizLocale;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getMinimumNumberCorrectAnswers() {
        return minimumNumberCorrectAnswers;
    }

    public void setMinimumNumberCorrectAnswers(int minimumNumberCorrectAnswers) {
        this.minimumNumberCorrectAnswers = minimumNumberCorrectAnswers;
    }
}

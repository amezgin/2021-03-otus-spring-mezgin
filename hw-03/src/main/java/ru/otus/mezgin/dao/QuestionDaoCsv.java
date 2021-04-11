package ru.otus.mezgin.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.enums.QuestionType;
import ru.otus.mezgin.errors.QuestionsFindException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final String questionFileName;

    private final List<Question> questions = new ArrayList<>();

    public QuestionDaoCsv(@Value("${questions.file.path}") String questionFileName) {
        this.questionFileName = questionFileName;
    }

    @Override
    public List<Question> findAll() throws QuestionsFindException {
        readCsv(this.questionFileName);
        return questions;
    }

    private void readCsv(String fileName) throws QuestionsFindException {
        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)))) {
            while ((line = reader.readLine()) != null) {
                try (Scanner scanner = new Scanner(line)) {
                    scanner.useDelimiter(";");
                    int indexInLine = 0;

                    Question question = new Question();

                    while (scanner.hasNext()) {
                        String data = scanner.next();
                        if (indexInLine == 0) {
                            question.setNumber(Integer.parseInt(data));
                        } else if (indexInLine == 1) {
                            question.setType(QuestionType.getEnumByName(data));
                        } else if (indexInLine == 2) {
                            question.setText(data);
                        } else if (indexInLine == 3) {
                            question.setCorrectAnswers(data.toLowerCase());
                        }
                        indexInLine++;
                    }
                    questions.add(question);
                } catch (Exception e) {
                    throw new QuestionsFindException(String.format("The file contains invalid data. File = %s", fileName));
                }
            }
        } catch (Exception e) {
            if (e instanceof QuestionsFindException) {
                throw new QuestionsFindException(e.getMessage());
            } else {
                throw new QuestionsFindException(String.format("File reading error. File = %s", fileName));
            }
        }
    }
}

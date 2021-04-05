package ru.otus.mezgin.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.enums.QuestionType;
import ru.otus.mezgin.errors.ReadCsvException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    @Value("${questions.file.path}")
    private String questionFileName;

    private final List<Question> questions = new ArrayList<>();

    @Override
    public List<Question> findAll() throws Exception {
        readCsv(this.questionFileName);
        return questions;
    }

    public QuestionDaoCsv() {
    }

    private void readCsv(String fileName) throws Exception {
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
                    throw new ReadCsvException(String.format("The file contains invalid data. File = %s", fileName));
                }
            }
        } catch (ReadCsvException e) {
            throw e;
        } catch (Exception e) {
            throw new ReadCsvException(String.format("File reading error. File = %s", fileName));
        }
    }
}

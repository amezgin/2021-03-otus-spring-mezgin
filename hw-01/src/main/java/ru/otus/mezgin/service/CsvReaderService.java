package ru.otus.mezgin.service;

import ru.otus.mezgin.domain.Answer;
import ru.otus.mezgin.domain.Item;
import ru.otus.mezgin.domain.Question;
import ru.otus.mezgin.domain.enums.QuestionType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CsvReaderService {

    private String questionFileName = "";

    private String answerFileName = "";

    private List<Question> questions = new LinkedList<>();

    private List<Answer> answers = new LinkedList<>();

    public CsvReaderService(String questionFileName, String answerFileName) {
        this.questionFileName = questionFileName;
        this.answerFileName = answerFileName;
    }

    public void readCsv() throws Exception {
        this.readCsv(this.questionFileName);
        this.readCsv(this.answerFileName);
        this.checkCountAnswersQuestions();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    private void readCsv(String fileName) {
        String line = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)))) {
            while ((line = reader.readLine()) != null) {
                try (Scanner scanner = new Scanner(line)) {
                    scanner.useDelimiter(";");
                    int indexInLine = 0;

                    Item item = null;

                    if (fileName.equals(this.questionFileName)) {
                        item = new Question();
                    } else {
                        item = new Answer();
                    }

                    while (scanner.hasNext()) {
                        String data = scanner.next();
                        if (indexInLine == 0) {
                            item.setNumber(Integer.parseInt(data));
                        } else if (indexInLine == 1) {
                            item.setType(QuestionType.getEnumByName(data));
                        } else if (indexInLine == 2) {
                            if (item instanceof Question) {
                                ((Question) item).setText(data);
                                questions.add((Question) item);
                            }
                            if (item instanceof Answer) {
                                String[] answersArr = data.replace(";", ".").trim().split(",");
                                ((Answer) item).setAnswers(Arrays.asList(answersArr));
                                answers.add((Answer) item);
                            }
                        } else {
                            System.out.println("Invalid data:" + data);
                        }
                        indexInLine++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkCountAnswersQuestions() throws Exception {
        if (this.questions.size() != this.answers.size()) {
            throw new Exception("The number of questions in the file does not match the number of answers in the file.");
        }
    }
}

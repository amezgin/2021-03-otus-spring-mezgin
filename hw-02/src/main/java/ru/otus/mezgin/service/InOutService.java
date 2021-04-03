package ru.otus.mezgin.service;

import ru.otus.mezgin.errors.ReadInputLineException;

public interface InOutService {

    void print(String msg);

    void println(String msg);

    String readLine() throws ReadInputLineException;
}

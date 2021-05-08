package ru.otus.mezgin.service;

import ru.otus.mezgin.errors.ReadInputLineException;

public interface InOutLocalizationWrapper {

    void print(String var);

    void print(String var, String[] args);

    void println(String var);

    void println(String var, String[] args);

    void printDefault(String var);

    String readLine() throws ReadInputLineException;

    String getLocalizedString(String var);

    String getLocalizedString(String var, String[] args);
}

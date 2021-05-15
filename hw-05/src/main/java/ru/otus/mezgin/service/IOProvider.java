package ru.otus.mezgin.service;

import java.io.InputStream;
import java.io.PrintStream;

public interface IOProvider {

    void setInputStream(InputStream inputStream);

    InputStream getInputStream();

    void setPrintStream(PrintStream printStream);

    PrintStream getPrintStream();
}

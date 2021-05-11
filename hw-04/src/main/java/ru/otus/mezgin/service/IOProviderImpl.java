package ru.otus.mezgin.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;

@Component
public class IOProviderImpl implements IOProvider {

    private PrintStream printStream = System.out;

    private InputStream inputStream = System.in;

    @Override
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override
    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public PrintStream getPrintStream() {
        return this.printStream;
    }
}

package ru.otus.mezgin.service;

import org.springframework.stereotype.Service;
import ru.otus.mezgin.errors.ReadInputLineException;

import java.util.Scanner;

@Service
public class ConsoleService implements InOutService {

    private final IOProvider ioProvider;

    public ConsoleService(IOProvider ioProvider) {
        this.ioProvider = ioProvider;
    }


    @Override
    public void print(String msg) {
        ioProvider.getPrintStream().print(msg);
    }

    @Override
    public void println(String msg) {
        ioProvider.getPrintStream().println(msg);
    }

    @Override
    public String readLine() throws ReadInputLineException {
        String str;
        try {
            Scanner scanner = new Scanner(ioProvider.getInputStream());
            str = scanner.nextLine();
        } catch (Exception e) {
            throw new ReadInputLineException(e.getMessage());
        }
        return str;
    }
}

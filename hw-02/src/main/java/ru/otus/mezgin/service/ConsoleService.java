package ru.otus.mezgin.service;

import org.springframework.stereotype.Service;
import ru.otus.mezgin.errors.ReadInputLineException;

import java.util.Scanner;

@Service
public class ConsoleService implements InOutService {

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }

    @Override
    public void println(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readLine() throws ReadInputLineException {
        String str;
        try {
            Scanner scanner = new Scanner(System.in);
            str = scanner.nextLine();
        } catch (Exception e) {
            throw new ReadInputLineException(e.getMessage());
        }
        return str;
    }
}

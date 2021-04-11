package ru.otus.mezgin.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.mezgin.errors.ReadInputLineException;
import ru.otus.mezgin.testdata.PersonData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("The ConsoleServiceTest class")
class ConsoleServiceTest {
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(PersonData.PERSON_NAME.getBytes());

    private final PrintStream printStream = new PrintStream(byteArrayOutputStream);

    @DisplayName("is checking print method.")
    @Test
    void printTest() {
        IOProvider ioProvider = new IOProviderImpl();
        ioProvider.setPrintStream(printStream);
        InOutService inOutService = new ConsoleService(ioProvider);
        String expectedGreetings = "Hello!";
        inOutService.print("Hello!");

        String actualGreetings = byteArrayOutputStream.toString();

        assertEquals(expectedGreetings, actualGreetings);
    }

    @DisplayName("is checking println method.")
    @Test
    void printlnTest() {
        IOProvider ioProvider = new IOProviderImpl();
        ioProvider.setPrintStream(printStream);
        InOutService inOutService = new ConsoleService(ioProvider);
        String expectedGreetings = "Hello!\r\n";
        inOutService.println("Hello!");

        String actualGreetings = byteArrayOutputStream.toString();

        assertEquals(expectedGreetings, actualGreetings);
    }

    @DisplayName("is checking readLine method.")
    @Test
    void readLineTest() throws ReadInputLineException {

        IOProvider ioProvider = new IOProviderImpl();
        ioProvider.setInputStream(byteArrayInputStream);
        InOutService inOutService = new ConsoleService(ioProvider);
        String expectedName = "Jhon";

        String actualName = inOutService.readLine();

        assertEquals(expectedName, actualName);
    }
}
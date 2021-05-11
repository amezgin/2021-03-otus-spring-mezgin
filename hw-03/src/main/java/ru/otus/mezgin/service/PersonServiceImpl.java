package ru.otus.mezgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.mezgin.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

    private final InOutService inOutService;

    @Autowired
    public PersonServiceImpl(InOutService inOutService) {
        this.inOutService = inOutService;
    }

    @Override
    public void askFullName() {
        inOutService.println("What is your name? Write your full name, please. For example, Alexander Mezgin.");
        inOutService.print("Your answer: ");
    }

    @Override
    public Person createPerson(String fullName) {
        while (fullName.contains("  ")) {
            fullName = fullName.replaceAll(" {2}", " ");
        }

        String[] fullNameArr = fullName.split(" ");

        Person person;
        if (fullNameArr.length == 0) {
            person = new Person("Anonim", "Anonim");
        } else if (fullNameArr.length == 1) {
            person = new Person(fullNameArr[0], "");
        } else {
            person = new Person(fullNameArr[0], fullNameArr[1]);
        }
        return person;
    }
}

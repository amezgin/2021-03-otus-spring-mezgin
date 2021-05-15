package ru.otus.mezgin.domain.enums;

public enum QuestionType {
    DEFAULT("DEFAULT"),
    MULTI("MULTI"),
    SINGLE("SINGLE"),
    EXTENDED("EXTENDED");

    QuestionType(String name) {
        this.name = name;
    }

    private final String name;

    public static QuestionType getEnumByName(String name) {
        QuestionType result = QuestionType.DEFAULT;
        for (QuestionType param : QuestionType.values()) {
            if (param.getName().equals(name)) {
                result = param;
                break;
            }
        }
        return result;
    }

    public String getName() {
        return name;
    }
}

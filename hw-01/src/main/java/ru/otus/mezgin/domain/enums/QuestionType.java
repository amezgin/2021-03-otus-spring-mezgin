package ru.otus.mezgin.domain.enums;

public enum QuestionType {
    DEFAULT("DEFAULT", "Value not found."),
    MULTY("MULTY", "Multiple choice."),
    SINGLE("SINGLE", "Single choice."),
    EXTENDED("EXTENDED", "Extended answer.");

    private QuestionType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private final String name;

    private final String description;

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

    public String getDescription() {
        return description;
    }
}

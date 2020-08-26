package main.java.duke.main;

import main.java.duke.exception.InvalidCommandException;

public enum Keyword {
    TODO("todo"),
    DEADLINE("deadline"),
    LIST("list"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye"),
    DONE("done"),
    FIND("find");

    private String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }

    public static boolean isValid(String inputKeyword) throws InvalidCommandException {
        Keyword[] array = Keyword.values();
        for (Keyword keyword : array) {
            if (keyword.keyword.equals(inputKeyword)) {
                return true;
            }
        }
        throw new InvalidCommandException();
    }
}

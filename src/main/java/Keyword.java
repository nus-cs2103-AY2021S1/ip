package main.java;

import java.security.Key;

public enum Keyword {
    TODO("todo"),
    DEADLINE("deadline"),
    LIST("list"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye"),
    DONE("done");

    private String keyword;

    Keyword(String keyword) {
        this.keyword = keyword;
    }

    public static boolean isValid(String inputKeyword) {
        Keyword[] array = Keyword.values();
        for (Keyword keyword : array) {
            if (keyword.keyword.equals(inputKeyword)) {
                return true;
            }
        }
        return false;
    }
}

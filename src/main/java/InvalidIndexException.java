package main.java;

public class InvalidIndexException extends DukeException {
    private static String message = "OOPS!!! The task index is empty :'(";

    @Override
    public String toString() {
        return message;
    }
}

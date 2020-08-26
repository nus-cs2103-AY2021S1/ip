package main.java.duke.exception;

public class EmptyDateException extends DukeException {
    private static String message = "OOPS!!! Please specify the date";

    @Override
    public String toString() {
        return message;
    }
}

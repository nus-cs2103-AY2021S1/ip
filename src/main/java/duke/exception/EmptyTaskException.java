package main.java.duke.exception;

public class EmptyTaskException extends DukeException {
    private static String message = "OOPS!!! The description of a task cannot be empty.";

    @Override
    public String toString() {
        return message;
    }
}

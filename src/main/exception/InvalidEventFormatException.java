package main.exception;

public class InvalidEventFormatException extends DukeException {
    public InvalidEventFormatException() {
        super("     ☹ OOPS!!! An event needs to have this format:\n"
                + "      \"task name\" /at \"event time\"");
    }
}

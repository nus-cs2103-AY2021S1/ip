package main.exception;

public class InvalidDeadlineFormatException extends DukeException {
    public InvalidDeadlineFormatException() {
        super("     ☹ OOPS!!! A deadline needs to have this format:\n"
                + "       \"task name\" /by \"task deadline\"");
    }
}

package exceptions;

public class EmptyDeadlineException extends DukeException {

    public EmptyDeadlineException() {
        super("\u2639 OOPS!!! The description of a deadline cannot be empty.");
    }
}

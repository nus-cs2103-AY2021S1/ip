package duke.exception;

public class EmptyDeadlineException extends Exception {
    public EmptyDeadlineException() {
        super("\uD83D\uDE41 OOPS! The description of a deadline cannot be empty.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}

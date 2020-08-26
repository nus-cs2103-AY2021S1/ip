package duke.exception;

public class MissingDeadlineDateException extends Exception {
    public MissingDeadlineDateException() {
        super("\uD83D\uDE41 OOPS! The date of a deadline cannot be empty.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}

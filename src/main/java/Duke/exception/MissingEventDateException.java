package duke.exception;

public class MissingEventDateException extends Exception {
    public MissingEventDateException() {
        super("\uD83D\uDE41 OOPS! The date of an event cannot be empty.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}

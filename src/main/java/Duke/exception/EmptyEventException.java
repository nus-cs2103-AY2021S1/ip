package duke.exception;

public class EmptyEventException extends Exception {
    public EmptyEventException() {
        super("\uD83D\uDE41 OOPS! The description of an event cannot be empty.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}

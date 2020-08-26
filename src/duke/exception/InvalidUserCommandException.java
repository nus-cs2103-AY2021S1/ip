package duke.exception;

public class InvalidUserCommandException extends IllegalArgumentException {
    public InvalidUserCommandException(String message) {
        super(message);
    }
}

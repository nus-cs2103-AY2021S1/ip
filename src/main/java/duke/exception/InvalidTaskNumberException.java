package duke.exception;

public class InvalidTaskNumberException extends InvalidInputException {
    public InvalidTaskNumberException() {
        super("The task number is invalid!");
    }

    public InvalidTaskNumberException(String message) {
        super(message);
    }
}

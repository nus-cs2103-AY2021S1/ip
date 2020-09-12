package exception;

/**
 * Exception that is thrown when the user enters an invalid command
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Input given is invalid! Please try again!");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}

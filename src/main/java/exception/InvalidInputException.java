package exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Input given is invalid! Please try again!");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}

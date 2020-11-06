package exceptions;

/**
 * Thrown if an invalid input is detected in a command that requires an integer input.
 */
public class InvalidNumberInputException extends DukeException {
    public InvalidNumberInputException(String message) {
        super(message);
    }
}

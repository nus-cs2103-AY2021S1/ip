package duke.exception;

/**
 * Exception thrown when the input is not a number.
 */
public class InvalidNumberInputException extends DukeException {
    public InvalidNumberInputException() {
        super("Sorry this is not a number.");
    }
}

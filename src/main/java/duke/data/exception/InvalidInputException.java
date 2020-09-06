package duke.data.exception;

/**
 * Custom Exception thrown when user input received is invalid.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("Sorry I'm not sure what you mean by that :/");
    }
}

package duke.exception;

/**
 * Exception thrown when the input is not in correct format.
 */
public class UnexpectedInputException extends DukeException {
    public UnexpectedInputException() {
        super("Sorry I don't specialise in gibberish.");
    }
}

package duke.response.exception;

/**
 * Represents an exception thrown when input by user is an unknown command.
 */
public class DukeUnknownInputException extends DukeInputException {
    /**
     * Class constructor.
     */
    public DukeUnknownInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that meows :-(");
    }
}

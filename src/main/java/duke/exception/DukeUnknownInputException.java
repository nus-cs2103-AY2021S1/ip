package duke.exception;

/**
 * Represents an exception thrown when input by user is an unknown command.
 */
public class DukeUnknownInputException extends DukeException {
    /**
     * Class constructor.
     */
    public DukeUnknownInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that meows :-(");
    }
}

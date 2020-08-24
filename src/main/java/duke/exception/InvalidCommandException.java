package duke.exception;

/**
 * Represents an exception when user keys in invalid commands
 */
public class InvalidCommandException extends DukeException{

    /**
     * Constructs an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}

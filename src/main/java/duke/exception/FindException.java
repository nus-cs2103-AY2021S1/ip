package duke.exception;

/**
 * The exception thrown when the user does not input any keyword when using find command.
 */
public class FindException extends DukeException {

    /**
     * Constructs a FindException with default message.
     * The message is "Please put in a keyword!"
     */
    public FindException() {
        super("Please put in a keyword!");
    }
}

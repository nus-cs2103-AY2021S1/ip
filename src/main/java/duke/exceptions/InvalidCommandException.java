package duke.exceptions;

/**
 * Represents an exception thrown when an invalid command is provided.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Represents an invalid command exception.
     */
    public InvalidCommandException() {
        super("Sorry, didn't get that :( "
                + "Try again or press 'help' to find out more!");
    }
}

package duke.exception;

/**
 * Exception thrown when a todo command has no description.
 */
public class InvalidToDoException extends DukeException {
    /**
     * Constructs an InvalidToDoException.
     */
    public InvalidToDoException() {
        super("The description of a todo cannot be empty :(\n"
                + "The command format is \"todo <description>\"");
    }
}

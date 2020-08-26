package duke;

/**
 * Invalid Description Exception that extends Duke Exception class.
 */
public class InvalidDescriptionException extends DukeException {
    /**
     * Constructor for InvalidDescriptionException class.
     */
    public InvalidDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty");
    }
}

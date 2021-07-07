package duke.exception;

/**
 * Represents an exception when user tried to list Task when there are no Task in the list.
 */
public class NoTaskException extends DukeException {

    /**
     * Constructs a NoTaskException.
     */
    public NoTaskException() {
        super("OOPS!!! You have no task currently.");
    }
}

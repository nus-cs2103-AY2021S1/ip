package duke.exception;

/**
 * Represents an exception when user tried to list tasks when there are no tasks.
 */
public class NoTaskException extends DukeException{

    /**
     * Constructs a NoTaskException.
     */
    public NoTaskException() {
        super("OOPS!!! You have no task currently.");
    }
}

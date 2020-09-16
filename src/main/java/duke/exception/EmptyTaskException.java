package duke.exception;

/**
 * Exception thrown when the task is empty.
 */
public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        super("A task cannot be empty.");
    }
}

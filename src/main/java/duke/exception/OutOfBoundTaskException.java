package duke.exception;

/**
 * Exception thrown when the task index is out of bound.
 */
public class OutOfBoundTaskException extends DukeException {
    public OutOfBoundTaskException() {
        super("Sorry there's no task with such index. Please enter a number in range.");
    }
}

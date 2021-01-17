package duke.exception;

/**
 * Encapsulates the exception when a task ID is expected, but none was provided.
 */
public class MissingTaskIdException extends DukeException {
    /**
     * Initialises a new instance with the specified detail message followed by a warning that
     * no task was specified.
     *
     * @param message The detail message.
     */
    public MissingTaskIdException(String message) {
        super(message + " No task was specified!");
    }
}

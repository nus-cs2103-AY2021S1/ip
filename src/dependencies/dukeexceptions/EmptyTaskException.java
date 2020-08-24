package dependencies.dukeexceptions;

/**
 * <p>This class extends DukeExceptions, and indicates that the user has passed in a command
 * regarding a task but did not specify the task.</p>
 *
 */
public class EmptyTaskException extends DukeException {
    /**
     * Constructs a new ExmptyTaskException with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public EmptyTaskException(String message) {
        super(message);
    }
}

package raythx.grandma.exception;

/**
 * Checked exception as a result of being unable to load task.
 */
public class TaskLoadingException extends DukeException {
    public TaskLoadingException() {
        super("Task loading error...");
    }
}

package duke.exception;

/**
 * Indicates a user command contains an invalid task list index.
 */
public class TaskDoesNotExistException extends IndexOutOfBoundsException {
    /**
     * Creates a new TaskDoesNotExistException with the specified index.
     *
     * @param index Invalid task list index.
     */
    public TaskDoesNotExistException(int index) {
        super("OOPS! Task " + index + " does not exist." + "\n" + "Please make sure task index is correct.");
    }
}

package duke.exception;

/**
 * Encapsulates a task index out of bounds exception. These exceptions are thrown when the user enters a done or delete
 * command but specifies an index that is out of the range of the task list.
 */
public class TaskIndexOutOfBoundsException extends DukeException {

    /** The index specified by the user */
    private String index;

    /**
     * Creates and initializes an TaskIndexOutOfBoundsException object.
     *
     * @param index The index specified by the user.
     */
    public TaskIndexOutOfBoundsException(String index) {
        this.index = index;
    }

    /**
     * Returns an error message. Informs the user that there is no task with the specified index.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " There is no task no. " + index;
    }
}

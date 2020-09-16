package duke.exception;

/**
 * Encapsulates a task index out of bounds exception. These exceptions are thrown when the user enters a done or delete
 * command but specifies an index that is out of the range of the task list.
 */
public class TaskIndexOutOfBoundsException extends DukeException {

    /**
     * Creates and initializes a TaskIndexOutOfBoundsException object.
     *
     * @param index The index specified by the user.
     */
    public TaskIndexOutOfBoundsException(String index) {
        super(" There is no task no. " + index);
    }
}

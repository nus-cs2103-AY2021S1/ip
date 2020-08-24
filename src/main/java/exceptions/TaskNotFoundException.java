package exceptions;

/**
 * Thrown if the user enters a number that is out of bounds when trying to delete or mark a task as done
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}

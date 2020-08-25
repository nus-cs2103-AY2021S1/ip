package duke.task;

/**
 * Represents an exception that happened in the Task logic.
 */
public class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}

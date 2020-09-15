package duke;

/**
 * TaskNotFoundException is thrown when task number does not correspond to any task.
 */
public class TaskNotFoundException extends Throwable {

    /**
     * Formats the string of TaskNotFoundException.
     *
     * @param message Takes in the error message to be printed.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}

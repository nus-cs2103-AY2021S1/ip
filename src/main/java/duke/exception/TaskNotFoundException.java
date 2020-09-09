package duke.exception;

/**
 * Represents a custom exception when the task number is not found.
 * @author Tee Kok Siang
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructs a TaskNotFoundException.
     */
    public TaskNotFoundException() {
        super("The task number is not found");
    }
}

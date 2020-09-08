package duke.Exception;

/**
 * Represents a custom exception when the task number is not found.
 * @author Tee Kok Siang
 */
public class TaskNotFoundException extends DukeException {
    public TaskNotFoundException() {
        super("The task number is not found");
    }
}

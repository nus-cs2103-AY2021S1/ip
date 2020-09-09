package duke.exception;

/**
 * Represents a custom exception when the {@link duke.task.Task} priority level is invalid.
 * @author Tee Kok Siang
 */
public class InvalidPriorityLevel extends DukeException {
    /**
     * Constructs a InvalidPriorityLevel.
     */
    public InvalidPriorityLevel() {
        super(":( Oops!!! Please type 1 for low priority; 2 for medium priority; 3 for high priority");
    }
}

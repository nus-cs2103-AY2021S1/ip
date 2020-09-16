package duke;

/**
 * Represents exception that signifies an invalid task.
 */
public class DukeInvalidTaskQueryException extends DukeException {

    /**
     * Class constructor.
     */
    public DukeInvalidTaskQueryException() {
        super("List does not have that task.");
    }
}

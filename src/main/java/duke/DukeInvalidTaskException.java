package duke;

/**
 * Represents exception that signifies an invalid task.
 */
public class DukeInvalidTaskException extends DukeException {

    /**
     * Class constructor.
     */
    public DukeInvalidTaskException() {
        super("List does not have that task.");
    }
}

package duke.exception;

/**
 * Represents exception to signify an invalid task.
 */
public class DukeInvalidTaskException extends DukeException {

    /**
     * Class constructor.
     */
    public DukeInvalidTaskException() {
        super("That task does not exist in the list!");
    }
}

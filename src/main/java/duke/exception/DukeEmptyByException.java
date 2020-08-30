package duke.exception;

/**
 * Represents an exception thrown when date is left empty for a Deadline.
 */
public class DukeEmptyByException extends DukeTaskException {
    /**
     * Class constructor.
     */
    public DukeEmptyByException() {
        super("☹ OOPS!!! The deadline cannot be empty.");
    }
}

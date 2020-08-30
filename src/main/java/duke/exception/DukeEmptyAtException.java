package duke.exception;

/**
 * Represents an exception thrown when date is left empty for an Event.
 */
public class DukeEmptyAtException extends DukeTaskException {
    /**
     * Class constructor.
     */
    public DukeEmptyAtException() {
        super("☹ OOPS!!! The event time cannot be empty.");
    }
}

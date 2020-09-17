package duke.exceptions;

/**
 * Represents an exception thrown when the timing of the event is not provided.
 */
public class EmptyEventTimingException extends DukeException {
    /**
     * Represents an empty event timing exception.
     */
    public EmptyEventTimingException() {
        super("Oh no, The duration of this task cannot be empty.\n"
                + "Please re-enter the desired event task\n"
                + "(e.g. event xxx /at yyyy-mm-dd HH:mm)");
    }
}

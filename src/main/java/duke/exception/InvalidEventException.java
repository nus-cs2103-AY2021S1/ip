package duke.exception;

/**
 * Thrown when the input to create an Event task is not formatted correctly.
 */
public class InvalidEventException extends DukeException {
    /**
     * Initializes the InvalidEventException object with the error message suggesting the proper format.
     */
    public InvalidEventException() {
        super("Event task is poorly formatted.\n    Here is a proper format: " +
                "'event name' /at 'start time to end time'" +
                "\n      e.g. meeting /at Sunday 2 - 4pm");
    }
}

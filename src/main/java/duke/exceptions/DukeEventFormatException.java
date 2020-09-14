package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the input format for the event command is invalid.
 */
public class DukeEventFormatException extends DukeException {
    public static final String ERROR_EVENT_FORMAT = "The description of 'event' should be accompaw-nied"
            + " by '/at' followed by the date in this format: 'yyyy-mm-dd'";

    public DukeEventFormatException() {
        super(ERROR_EVENT_FORMAT);
    }
}

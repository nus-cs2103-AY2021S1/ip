package duke.exception;

/**
 * The exception thrown when the user does not input any description
 * for the event's task name or does not use /at to specify the
 * task's date.
 */
public class EventException extends DukeException {

    /**
     * Constructs an EventException with default message.
     * The message is "OOPS!!! The description or time of an event cannot be empty."
     */
    public EventException() {
        super("OOPS!!! The description or time of an event cannot be empty.");
    }
}

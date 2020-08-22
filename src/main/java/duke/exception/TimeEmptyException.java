package duke.exception;

/**
 * Represents the exceptions that user provides empty time for event and deadline.
 */
public class TimeEmptyException extends DukeException {
    public TimeEmptyException(String type) {
        super(String .format(" ☹ OOPS!!! The time of a %s cannot be empty.",type));
    }
}

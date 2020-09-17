package duke.exceptions;

/**
 * Represents an Event Empty Body Exception. An <code>Event Empty Body
 * Exception</code> object is used when "event" is called without further
 * information
 */
public class EventEmptyBodyException extends EmptyBodyException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public EventEmptyBodyException() { }

    /** Returns a string message for event empty body exception.
     *
     */
    @Override
    public String toString() {
        return "The description of an event cannot be empty.";
    }
}

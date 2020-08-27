/**
 * Represents an Event Empty Body Exception. An <code>Event Empty Body
 * Exception</code> object is used when "event" is called without further
 * information
 */
public class EventEmptyBodyException extends EmptyBodyException {
    EventEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }
}
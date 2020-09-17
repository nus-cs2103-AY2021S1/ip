package duke.exceptions;

/**
 * Represents an Abstract Empty Body Exception. A <code>EmptyBody
 * Exception</code> object is used when a command is called with
 * empty body
 */
public abstract class EmptyBodyException extends IndexOutOfBoundsException {
    EmptyBodyException() { }
}

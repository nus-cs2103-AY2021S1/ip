package duke_exceptions;

/**
 * Represents a Toodo Empty Body Exception. A <code>Todoo EmptyBody
 * Exception</code> object is used when "todoo" is called without further
 * information
 */
public class TodoEmptyBodyException extends EmptyBodyException {
    TodoEmptyBodyException() {}
    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}

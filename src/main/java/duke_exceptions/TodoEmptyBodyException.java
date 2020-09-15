package duke_exceptions;

/**
 * Represents a Todo Empty Body Exception. A <code>Todo EmptyBody
 * Exception</code> object is used when "todo" is called without further
 * information
 */
public class TodoEmptyBodyException extends EmptyBodyException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public TodoEmptyBodyException() { }

    /** Returns a string message for todo empty body exception.
     *
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}

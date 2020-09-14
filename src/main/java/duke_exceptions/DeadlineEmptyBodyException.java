package duke_exceptions;

/**
 * Represents a Deadline Empty Body Exception. A <code>Deadline EmptyBody
 * Exception</code> object is used when "deadline" is called without further
 * information
 */

public class DeadlineEmptyBodyException extends EmptyBodyException {
    DeadlineEmptyBodyException() {}
    @Override
    public String toString() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}
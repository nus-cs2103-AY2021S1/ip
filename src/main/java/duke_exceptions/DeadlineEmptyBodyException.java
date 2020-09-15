package duke_exceptions;

/**
 * Represents a Deadline Empty Body Exception. A <code>Deadline EmptyBody
 * Exception</code> object is used when "deadline" is called without further
 * information
 */

public class DeadlineEmptyBodyException extends EmptyBodyException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public DeadlineEmptyBodyException() { }

    /** Returns a string message for deadline empty body exception.
     *
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}

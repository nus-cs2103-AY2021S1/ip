package duke_exceptions;

/**
 * Represents a Delete Empty Body Exception. A <code>Delete EmptyBody
 * Exception</code> object is used when "delete" is called without further
 * information
 */
public class DeleteEmptyBodyException extends EmptyBodyException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public DeleteEmptyBodyException() { }

    /** Returns a string message for delete empty body exception.
     *
     */
    @Override
    public String toString() {
        return "Empty deletion is invalid.";
    }
}

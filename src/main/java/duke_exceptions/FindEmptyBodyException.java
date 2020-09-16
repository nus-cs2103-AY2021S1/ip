package duke_exceptions;

/**
 * Represents a Find Empty Body Exception. A <code>Find EmptyBody
 * Exception</code> object is used when "find" is called without further
 * information
 */
public class FindEmptyBodyException extends EmptyBodyException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public FindEmptyBodyException() { }

    /** Returns a string message for find empty body exception.
     *
     */
    @Override
    public String toString() {
        return "Empty find is invalid.";
    }
}

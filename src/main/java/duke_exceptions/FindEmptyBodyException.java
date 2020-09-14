package duke_exceptions;

/**
 * Represents a Find Empty Body Exception. A <code>Find EmptyBody
 * Exception</code> object is used when "find" is called without further
 * information
 */
public class FindEmptyBodyException extends EmptyBodyException {
    public FindEmptyBodyException() {}
    @Override
    public String toString() {
        return "OOPS!!! Empty find is invalid.";
    }
}
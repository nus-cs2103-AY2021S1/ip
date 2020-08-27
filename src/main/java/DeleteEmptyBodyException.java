/**
 * Represents a Delete Empty Body Exception. A <code>Delete EmptyBody
 * Exception</code> object is used when "delete" is called without further
 * information
 */
public class DeleteEmptyBodyException extends EmptyBodyException {
    DeleteEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Empty deletion is invalid.";
    }
}
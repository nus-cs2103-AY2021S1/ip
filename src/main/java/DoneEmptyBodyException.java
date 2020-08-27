/**
 * Represents a Done Empty Body Exception. A <code>Done EmptyBody
 * Exception</code> object is used when "done" is called without further
 * information
 */
public class DoneEmptyBodyException extends EmptyBodyException {
    DoneEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Empty done is invalid.";
    }
}
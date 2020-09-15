package duke_exceptions;

/**
 * Represents a Done Empty Body Exception. A <code>Done EmptyBody
 * Exception</code> object is used when "done" is called without further
 * information
 */
public class DoneEmptyBodyException extends EmptyBodyException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public DoneEmptyBodyException() { }

    /** Returns a string message for done empty body exception.
     *
     */
    @Override
    public String toString() {
        return "OOPS!!! Empty done is invalid.";
    }
}

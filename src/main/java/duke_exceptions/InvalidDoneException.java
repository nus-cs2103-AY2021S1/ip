package duke_exceptions;

/**
 * Represents an Invalid Done Exception . An <code>Invalid Done Exception
 * </code> object is used when attempted done index is invalid
 */
public class InvalidDoneException extends Exception {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public InvalidDoneException() { }

    /** Returns a string message for invalid done exception.
     *  (Index out of bounds)
     *
     */
    @Override
    public String toString() {
        return "OOPS!!! Done index is invalid.";
    }
}

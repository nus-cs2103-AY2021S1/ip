package duke_exceptions;

/**
 * Represents an Invalid Done Exception . An <code>Invalid Done Exception
 * </code> object is used when attempted done index is invalid
 */
public class InvalidDoneException extends Exception {
    public InvalidDoneException() {}
    @Override
    public String toString() {
        return "OOPS!!! Done index is invalid.";
    }
}
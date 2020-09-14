package duke_exceptions;

/**
 * Represents an Invalid Deletion Exception . An <code>Invalid Deletion Exception
 * </code> object is used when attempted deletion index is invalid
 */
public class InvalidDeletionException extends Exception {
    public InvalidDeletionException() {}
    @Override
    public String toString() {
        return "OOPS!!! Deletion index is invalid.";
    }
}
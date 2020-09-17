package duke.exceptions;

/**
 * Represents an Invalid Deletion Exception. An <code>Invalid Deletion
 * Exception </code> object is used when attempted deletion index
 * is invalid
 */
public class InvalidDeletionException extends Exception {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public InvalidDeletionException() { }

    /** Returns a string message for invalid deletion exception.
     *  (Index out of bounds)
     */
    @Override
    public String toString() {
        return "Deletion index is invalid.";
    }
}

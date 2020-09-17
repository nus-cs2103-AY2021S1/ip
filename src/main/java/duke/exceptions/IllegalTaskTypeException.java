package duke.exceptions;

/**
 * Represents an Illegal Task Type Exception. An <code>Illegal Task Type
 * Exception</code> object is used when invalid task type is detected
 */
public class IllegalTaskTypeException extends Exception {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public IllegalTaskTypeException() { }

    /** Returns a string message for illegal task type body exception.
     *
     */
    @Override
    public String toString() {
        return "Cannot detect task type";
    }
}


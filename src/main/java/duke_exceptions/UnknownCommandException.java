package duke_exceptions;

/**
 * Represents a Unknown Command Exception. A <code>Unknown Command
 * Exception</code> object is used when an unknown command is called
 */
public class UnknownCommandException extends IndexOutOfBoundsException {
    /** Empty constructor as only toString method is repetitively used.
     *
     */
    public UnknownCommandException() { }

    /** Returns a string message for unknown command type exception.
     *
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";

    }
}

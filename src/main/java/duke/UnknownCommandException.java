package duke;

/**
 * Represents an exception that happened when the command received is not recognised.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}

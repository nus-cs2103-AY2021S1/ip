package duke;

/**
 * Represents an exception that may be thrown while running Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates a new Duke Exception that can be thrown with the specified message.
     *
     * @param message the message that is to be shown when a Duke Exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}

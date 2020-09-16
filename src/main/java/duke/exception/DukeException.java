package duke.exception;

/**
 * Represents an Exception that is made when executing Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates an Exception with a message.
     *
     * @param message Message to be displayed on the UI in the event
     *                that an Exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }

}

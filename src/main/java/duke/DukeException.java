package duke;

/**
 * DukeException messages for Duke.
 */
public class DukeException extends Exception {

    /**
     * Initializes DukeException without a message.
     */
    public DukeException() {
        super();
    }

    /**
     * Initializes DukeException with a message.
     *
     * @param message The message of the error.
     */
    public DukeException(String message) {
        super(message);
    }
}

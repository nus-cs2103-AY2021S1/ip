package duke.exception;

/**
 * Exception that is thrown by the bot when encountered with invalid actions from the user.
 */
public class DukeException extends Exception {

    /**
     * Creates a new {@code DukeException} instance.
     *
     * @param message Error message to be shown to the user
     */
    public DukeException(String message) {
        super(message);
    }
}

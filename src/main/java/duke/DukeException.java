package duke;

/**
 * Handle error when using invalid command.
 */
public class DukeException extends Exception {
    /**
     * Constructor.
     * @param message Message to show the user.
     */
    public DukeException(String message) {
        super(message);
    }
}

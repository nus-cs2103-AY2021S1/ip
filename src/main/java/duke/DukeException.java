package duke;

/**
 * Handles any exception arising from wrong commands
 */
public class DukeException extends Exception {
    /**
     * Constructor from creating DukeException object
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }
}

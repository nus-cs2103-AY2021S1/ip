package duke;

public class DukeException extends Exception {

    /**
     * Throws a new DukeException
     * @param message Message to be displayed in exception.
     */
    public DukeException(String message) {
        super("Action failed: " + message);
    }
}

package raythx.grandma.exception;

/**
 * Checked exception as a result of an invalid index.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("Wrung index la... Choose a task tat exist pls grr...");
    }
}

package duke.exceptions;

/**
 * Thrown when the user inputs an invalid index to be marked as done.
 */
public class InvalidDoneCommandException extends DukeException {
    public InvalidDoneCommandException() {
        super("\u2639 OOPS!!! Please mark a valid item as done");
    }
}

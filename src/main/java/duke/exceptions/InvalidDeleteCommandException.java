package duke.exceptions;

/**
 * Thrown when the user inputs an invalid index to be marked for deletion.
 */
public class InvalidDeleteCommandException extends DukeException {

    public InvalidDeleteCommandException() {
        super("\u2639 OOPS!!! Please mark a valid item for deletion");
    }
}

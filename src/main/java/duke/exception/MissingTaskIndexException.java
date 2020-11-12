package duke.exception;

/**
 * Exception thrown when the command does not have task index.
 */
public class MissingTaskIndexException extends DukeException {
    public MissingTaskIndexException() {
        super("Sorry what's you task index again?");
    }
}

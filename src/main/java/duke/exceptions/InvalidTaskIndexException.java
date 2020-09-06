package duke.exceptions;

/** Represents the Duke exception which is thrown when the user inputs a task index that is out of the list's range. */
public class InvalidTaskIndexException extends DukeException {

    /** Constructs a new InvalidTaskIndexException object with the specified error message. */
    public InvalidTaskIndexException() {
        super("Harh? This task index does not exist in your list.");
    }
}

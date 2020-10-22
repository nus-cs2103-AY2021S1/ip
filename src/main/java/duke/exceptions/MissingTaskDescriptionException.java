package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not input a description to a task. */
public class MissingTaskDescriptionException extends DukeException {

    /** Constructs a new MissingTaskDescriptionException object with the specified error message. */
    public MissingTaskDescriptionException() {
        super("Harh? The description of a task cannot be empty.");
    }
}

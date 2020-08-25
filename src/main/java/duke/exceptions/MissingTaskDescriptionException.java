package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not input a description to a task. */
public class MissingTaskDescriptionException extends DukeException {

    /** Constructor. */
    public MissingTaskDescriptionException() {
        super("☹ OOPS!!! The description of a task cannot be empty.");
    }
}

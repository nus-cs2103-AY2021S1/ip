package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not input a task index in a Done/Delete Command. */
public class MissingTaskIndexException extends DukeException {

    /** Constructor. */
    public MissingTaskIndexException() {
        super("☹ OOPS!!! There isn't a task index inputted");
    }
}

package duke.exception;

/**
 * The exception thrown when the user does not input any number
 * in order to mark a task as done.
 */
public class DoneException extends DukeException {

    /**
     * Constructs a DoneException with default message.
     * The message is "OOPS!!! You need a task number to use done!."
     */
    public DoneException() {
        super("OOPS!!! You need a task number to use done!");
    }
}

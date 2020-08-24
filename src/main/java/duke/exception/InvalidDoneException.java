package duke.exception;

/**
 * Represents an exception when user does not input a task number to mark as done.
 */
public class InvalidDoneException extends DukeException{

    /**
     * Constructs an InvalidDoneException.
     */
    public InvalidDoneException() {
        super("OOPS!!! The task to be done has to be a number.");
    }
}

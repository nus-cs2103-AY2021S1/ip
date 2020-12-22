package duke.exceptions;

/**
 * Represents an exception thrown when the deadline of the task is not provided.
 */
public class EmptyDeadlineException extends DukeException {
    /**
     * Represents an empty deadline exception.
     */
    public EmptyDeadlineException() {
        super("Oh no, the deadline of this task is not provided.\n"
                + "Please re-enter the desired deadline task\n"
                + "(e.g. deadline xxx /by yyyy-mm-dd HH:MM)");
    }
}

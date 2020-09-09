/**
 * Represents an exception thrown when the deadline of the task is not provided.
 */
public class EmptyDeadlineException extends DukeException {
    EmptyDeadlineException() {
        super("The deadline of this task is not provided.\n"
                + "Please re-enter the desired deadline task\n"
                + "(e.g. deadline xxx /by yyyy-mm-dd HH:MM)");
    }
}

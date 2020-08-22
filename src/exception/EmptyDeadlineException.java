package exception;

/**
 * Represents an exception stating the description of the <code>Deadline</code> to be added is empty.
 */
public class EmptyDeadlineException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The description or deadline of a deadline cannot be empty. Format: " +
                "deadline [description] /by [deadline]";
    }
}

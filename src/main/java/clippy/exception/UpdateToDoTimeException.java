package clippy.exception;

/**
 * Represents an exception due to user trying to update the date/time of a todo task.
 */
public class UpdateToDoTimeException extends ClippyException {
    /**
     * Constructs an UpdateToDoTimeException with a pre-defined error message.
     */
    public UpdateToDoTimeException() {
        super("ToDos do not have a date or time! Please retry with only task description. No changes made.");
    }
}

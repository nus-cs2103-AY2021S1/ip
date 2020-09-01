package duke.dukeexception;

/**
 * Exception thrown when the description is missing from is missing for the
 * <code>AddCommand</code>, <code>DeleteCommand</code> or <code>DoneCommand</code>
 * bot commands.
 */
public class NoDescriptionException extends DukeException {

    public NoDescriptionException(String cmd) {
        super("The description of " + cmd + " cannot be empty lah. Try again!");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

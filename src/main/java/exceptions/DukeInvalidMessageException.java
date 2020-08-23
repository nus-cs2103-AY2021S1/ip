package exceptions;

/**
 * If a command entered does not have a valid description,
 * <tt>DukeInvalidMessageException</tt> will be thrown.
 */
public class DukeInvalidMessageException extends DukeException {

    /**
     * Specific message that will be sent to user
     * when description of command is invalid.
     * An example would be putting done or delete description which
     * does not exist in the list.
     */
    public DukeInvalidMessageException() {
        super("Sorry Boss! The description that you put is invalid..");
    }
}

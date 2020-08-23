package exceptions;

/**
 * If command is unknown, <tt>DukeUnknownCommandException</tt>
 * will be thrown.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Override the message to be sent to user when
     * the command input by user is unknown.
     */
    public DukeUnknownCommandException() {
        super("Oops! I'm sorry Boss! I do not know what that means :-(");
    }
}

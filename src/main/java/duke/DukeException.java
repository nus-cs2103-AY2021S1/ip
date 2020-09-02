package duke;

/**
 * The DukeException class implements a DukeException, exception thrown
 * when errors occur in the Duke chat-bot.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class DukeException extends Exception {

    /**
     * Instantiates a DukeException that shows error that occurred.
     * @param message String message showed describing the error.
     */
    public DukeException(String message) {
        super(message);
    }
}

package exceptions;

/**
 * If there is any error related to duke chatbot,
 * <tt>DukeException</tt> will be thrown.
 */
public class DukeException extends Exception {

    /**
     * Same message will be thrown to user in DukeException.
     * @param message message that will be thrown to user
     */
    public DukeException(String message) {
        super(message);
    }
}

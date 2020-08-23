package exceptions;

/**
 * If a command does not have any descriptions,
 * then <tt>DukeEmptyMessageException</tt> will be thrown.
 */
public class DukeEmptyMessageException extends DukeException {

    /**
     * Returns a specific message depending on the command
     * that is passed as parameter.
     *
     * @param message command given to the chatbot.
     */
    public DukeEmptyMessageException(String message) {
        super("Oops! " + message + " cannot have an empty message!");
    }
}

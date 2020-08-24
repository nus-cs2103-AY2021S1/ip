package duke.exception;

/**
 * Represents an exception thrown by chat bot.
 */
public class DukeException extends Exception {

    /**
     * Class constructor
     * @param msg String of error message.
     */
    public DukeException(String msg) {
        super(msg);
    }
}

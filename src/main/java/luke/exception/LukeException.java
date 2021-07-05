package luke.exception;

/**
 * Represents an exception thrown when executing Luke.
 */
public class LukeException extends Exception {
    /**
     * Creates a LukeException object that contains error message.
     *
     * @param msg message containing error details
     */
    public LukeException(String msg) {
        super(msg);
    }
}

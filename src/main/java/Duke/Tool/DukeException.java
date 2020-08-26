package Duke.Tool;

/**
 * Represents the exception thrown by Duke.
 */
public class DukeException extends Exception {

    /**
     * Construct a Duke exception.
     * @param message message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Output exception as a string.
     * @return string representation of the exception.
     */
    @Override
    public String toString() {
        String emoji = Emoji.ERROR.toString();
        String msgForException = "    ____________________________________________________________\n"
                + "    " + emoji + this.getMessage() + "\n"
                + "    ____________________________________________________________\n";
        return msgForException;
    }
}

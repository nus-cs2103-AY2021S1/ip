package duke.tool;

/**
 * Represents the exception thrown by duke.Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a duke.Duke exception.
     * @param message message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Outputs exception as a string.
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

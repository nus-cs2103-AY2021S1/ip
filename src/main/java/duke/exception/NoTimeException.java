package duke.exception;

/**
 * The exception to be thrown when Duke encounters an empty time.
 */
public class NoTimeException extends Exception {
    /**
     * Construct a new exception with the type of task.
     * @param type the type of task where the exception is encountered
     */
    public NoTimeException(String type) {
        super("☹ OOPS!!! The time of a " + type + " cannot be empty.");
    }
}

package duke.exception;

/**
 * The exception to be thrown when Duke encounters an empty description.
 */
public class NoDescriptionException extends Exception {
    /**
     * Construct a new exception with the type of task.
     * @param type the type of task where the exception is encountered
     */
    public NoDescriptionException(String type) {
        super("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }
}

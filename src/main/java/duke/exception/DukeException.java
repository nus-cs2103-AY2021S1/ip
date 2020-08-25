package duke.exception;

/**
 * Encapsulates possible exception that can occur from the {@link duke} package.
 */
public class DukeException extends Exception {
    /**
     * Initializes a new instance with a <code>☹ OOPS!!!</code> followed by the error message.
     *
     * @param message the error message.
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

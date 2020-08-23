package duke.exception;

/**
 * Encapsulates possible exception that can occur from the {@link duke} package.
 */
public class DukeException extends Exception {
    /**
     * Initialises a new instance with a <code>☹ OOPS!!! </code> to represent the personality of
     * the chat bot, followed by the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}

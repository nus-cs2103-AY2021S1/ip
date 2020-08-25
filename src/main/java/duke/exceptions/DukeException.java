package duke.exceptions;

/**
 * Represents an {@code Exception} that is related to <i>Duke</i>.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new {@code DukeException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}

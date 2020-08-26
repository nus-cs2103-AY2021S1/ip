package duke.exception;

/**
 * {@code DukeException} is an all-in-one exception used by {@code Duke} programme
 *      to wrap and display exceptions in a user-friendly message.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructor of a {@code DukeException}.
     *
     * @param msg Message to be displayed for user.
     */
    public DukeException(String msg) {
        super(":( \n\tI dont understand this... \n\t" + msg);
    }
}

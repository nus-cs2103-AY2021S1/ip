package duke.error;

/**
 * The base error class for other Duke-related errors to inherit from.
 */
public class DukeError extends Exception {
    DukeError(String msg) {
        super(msg);
    }
}

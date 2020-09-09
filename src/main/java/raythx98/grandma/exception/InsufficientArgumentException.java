package raythx98.grandma.exception;

/**
 * Checked exception as a result of insufficient arguments.
 */
public class InsufficientArgumentException extends DukeException {
    public InsufficientArgumentException() {
        super("Not enuff inputs leh... Wake up ur bloody idea...");
    }
}

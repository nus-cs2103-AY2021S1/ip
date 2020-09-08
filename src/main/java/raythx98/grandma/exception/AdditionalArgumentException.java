package raythx98.grandma.exception;

/**
 * Checked exception as a result of an empty description.
 */
public class AdditionalArgumentException extends DukeException {
    public AdditionalArgumentException() {
        super("Wtf why got so many inputs... Wake up ur bloody idea...");
    }
}

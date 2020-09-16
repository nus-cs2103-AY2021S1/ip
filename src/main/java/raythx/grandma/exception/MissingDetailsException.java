package raythx.grandma.exception;

/**
 * Checked exception as a result of missing description or tag.
 */
public class MissingDetailsException extends DukeException {
    public MissingDetailsException() {
        super("Sumtin wong with yur description or #tag..?");
    }
}

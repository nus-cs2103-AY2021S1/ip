package raythx.grandma.exception;

/**
 * Checked exception as a result of missing description or tag.
 */
public class MissingDetailsException extends DukeException {
    public MissingDetailsException() {
        super("Whr da hell yur description or #tag at..?");
    }
}

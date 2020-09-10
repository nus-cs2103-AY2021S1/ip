package raythx.grandma.exception;

/**
 * Checked exception as a result of an empty description.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("Empty description la... Wut u wan me do with dis grr...");
    }
}

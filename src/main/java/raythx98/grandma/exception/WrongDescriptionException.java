package raythx98.grandma.exception;

/**
 * Checked exception as a result of an empty description.
 */
public class WrongDescriptionException extends DukeException {
    public WrongDescriptionException() {
        super("Wrong description la... Wut u wan me do with dis grr...");
    }
}

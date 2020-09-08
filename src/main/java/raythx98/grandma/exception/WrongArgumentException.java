package raythx98.grandma.exception;

/**
 * Checked exception as a result of an empty description.
 */
public class WrongArgumentException extends DukeException {
    public WrongArgumentException() {
        super("Wrong input la... Wut u wan me do with dis grr...");
    }
}

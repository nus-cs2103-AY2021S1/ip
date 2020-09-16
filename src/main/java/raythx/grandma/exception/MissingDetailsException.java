package raythx.grandma.exception;

/**
 * Checked exception as a result of being unable to load task.
 */
public class MissingTagException extends DukeException {
    public MissingTagException() {
        super("Whr da hell yur #tag at..?");
    }
}

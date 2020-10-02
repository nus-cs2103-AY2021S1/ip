package raythx.grandma.exception;

/**
 * Checked exception as a result of an unknown command.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Wtf yu typing to mi? Even grandma type bettur than yu");
    }
}

package raythx.grandma.exception;

/**
 * Checked exception as a result of illegal inputs.
 */
public class IllegalInputException extends DukeException {
    public IllegalInputException() {
        super("Don't anyhow anyhow add funky characters like |...");
    }
}

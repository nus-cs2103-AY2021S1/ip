package data.exception;

/**
 * Signals that a given command is not recognised by Duke.
 */
public class DukeIllegalCommandException extends DukeException {
    /**
     * @param command that is not recognised by Duke.
     */
    public DukeIllegalCommandException(String command) {
        super("I'm terribly sorry to inform you that " + command + " is not an unrecognizable command.");
    }
}

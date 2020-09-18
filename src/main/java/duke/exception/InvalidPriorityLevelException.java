package duke.exception;

/**
 * Represents the exception where the user does not enter an appropriate priority level.
 */
public class InvalidPriorityLevelException extends DukeException {

    /**
     * Creates an InvalidPriorityLevelException object.
     */
    public InvalidPriorityLevelException() {
        super("Oops that wasn't a valid priority level!\n");
    }
}

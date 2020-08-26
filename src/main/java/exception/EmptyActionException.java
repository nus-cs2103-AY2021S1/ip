package exception;

/**
 * Encapsulates the message of an exception or error related to Duke's operation
 *  on empty command or action.
 *
 * <p>The 'EmptyActionException' supports operators, supported include: </p>
 *
 * <p> (i) Getters to error message </p>
 */
public class EmptyActionException extends exception.DukeException {
    private static final String DESCRIPTION = "The description of a command cannot be empty.";

    /**
     * Constructor of this object.
     */
    public EmptyActionException() {
        super(DESCRIPTION);
    }
}
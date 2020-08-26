package exception;

/**
 * Encapsulates the message of an exception or error related to Duke's invalid
 *  action on command operation.
 *
 * <p>The 'InvalidActionException' supports operators, supported include: </p>
 *
 * <p> (i) Getters to error message </p>
 */
public class InvalidActionException extends DukeException {
    private static final String DESCRIPTION = "The action is invalid.";

    /**
     * Constructor of this object.
     */
    public InvalidActionException() {
        super(DESCRIPTION);
    }

    /**
     * Constructor of this object.
     * @param message error message of this exception.
     */
    public InvalidActionException(String message) {
        super(message);
    }
}
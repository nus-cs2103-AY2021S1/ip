package exception;

/**
 * Encapsulates the message of an exception or error related to Duke's operation on
 * invalid commands.
 *
 * <p>The 'InvalidCommandException' supports operators, supported include: </p>
 *
 * <p> (i) Getters to error message </p>
 */
public class InvalidCommandException extends DukeException {
    private static final String DESCRIPTION = "I'm sorry, but I don't know what that means :-(";

    /**
     * Constructor of this object.
     */
    public InvalidCommandException() {
        super(DESCRIPTION);
    }
}
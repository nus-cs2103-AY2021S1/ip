package duke.exception;

/**
 * Encapsulates unknown command exceptions. These exceptions are thrown when the user enters an invalid command in
 * which the first word of the command is not a recognized command word.
 */
public class UnknownCommandException extends DukeException {

    private static final String additionalErrorDescription = " I'm sorry, but I don't know what that means :(";

    /**
     * Creates and initializes an UnknownCommandException object.
     */
    public UnknownCommandException() {
        super(additionalErrorDescription);
    }
}

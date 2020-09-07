package duckie.exception;

/**
 * DuckieException thrown when Duckie does not understand the command.
 */
public class DuckieInvalidCommandException extends DuckieException {

    /**
     * Instantiates DuckieInvalidCommandException.
     */
    public DuckieInvalidCommandException() {
        super("Sorry, Duckie does not understand what you are trying to do.");
    }

}

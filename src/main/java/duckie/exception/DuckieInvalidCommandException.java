package duckie.exception;

/**
 * DuckieException thrown when Duckie does not understand the command
 */
public class DuckieInvalidCommandException extends DuckieException {
    protected final static String INDENT = "\t";

    /**
     * Instantiate DuckieInvalidCommandException
     */
    public DuckieInvalidCommandException() {
        super(INDENT + "Sorry, Duckie does not understand what you are trying to do.");
    }

}

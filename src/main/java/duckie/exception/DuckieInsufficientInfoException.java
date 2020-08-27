package duckie.exception;

/**
 * DuckieException thrown when there are insufficient info input
 */
public class DuckieInsufficientInfoException extends DuckieException {
    protected final static String INDENT = "\t";

    /**
     * Instantiate DuckieInsufficientInfoErrorException
     */
    public DuckieInsufficientInfoException() {
        super(INDENT + "Duckie needs more description after the first word.");
    }
}

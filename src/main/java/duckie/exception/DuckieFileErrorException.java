package duckie.exception;

/**
 * DuckieException thrown when facing errors loading duckie file
 */
public class DuckieFileErrorException extends DuckieException {
    protected static final String INDENT = "\t";

    /**
     * Instantiate DuckieFileErrorException
     */
    public DuckieFileErrorException() {
        super(INDENT + "Duckie is facing some problems loading your file.");
    }
}

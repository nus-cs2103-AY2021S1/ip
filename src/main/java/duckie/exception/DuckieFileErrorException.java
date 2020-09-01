package duckie.exception;

/**
 * DuckieException thrown when facing errors loading duckie file
 */
public class DuckieFileErrorException extends DuckieException {

    /**
     * Instantiate DuckieFileErrorException
     */
    public DuckieFileErrorException() {
        super("Duckie is facing some problems loading your file.");
    }
}

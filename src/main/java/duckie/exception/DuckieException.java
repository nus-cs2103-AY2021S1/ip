package duckie.exception;

/**
 * Parent class of all the possible DuckieExceptions.
 */
public class DuckieException extends Exception {
    /**
     * Instantiates DuckieException.
     *
     * @param message Error message of why the Exception is thrown.
     */
    public DuckieException(String message) {
        super(message);
    }
}

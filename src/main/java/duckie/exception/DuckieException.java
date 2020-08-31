package duckie.exception;

/**
 * Parent class of all the possible DuckieExceptions
 */
public class DuckieException extends Exception {
    /**
     * Instantiate DuckieException
     * @param message Error message of why the Exception is thrown
     */
    public DuckieException(String message) {
        super(message);
    }

    /**
     * Overrides method to show the message of a DuckieException
     * @return
     */
    @Override
    public String toString() {
        return getMessage();
    }
}

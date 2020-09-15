package exception;

/**
 * Exception for commands with missing key information.
 */
public class MissingInfoException extends Exception {
    public MissingInfoException(String message) {
        super(message);
    }
}

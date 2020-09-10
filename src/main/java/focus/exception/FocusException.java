package focus.exception;

/** Represents the exceptions for Focus, and allows other exceptions to extend. */
public class FocusException extends Exception {
    /**
     * Creates FocusException for methods to throw.
     *
     * @param message Error message.
     */
    public FocusException(String message) {
        super("\tERROR: " + message);
    }
}

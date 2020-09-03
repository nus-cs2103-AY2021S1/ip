package exceptions;

/**
 * Represents an exception thrown by Duke. The message corresponds to
 * the problem encountered and how to mitigate it.
 */
public class DukeException extends Exception {

    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the message indicating the problem and its mitigation.
     * @return the error message
     */
    public String getMessage() {
        return this.message;
    }
}

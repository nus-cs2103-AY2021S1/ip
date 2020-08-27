package main.java.exceptions;

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
     */
    public String getMessage() {
        return this.message;
    }
}

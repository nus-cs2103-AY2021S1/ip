package main.java.duke.exception;

/**
 * Represents an exception related to program operations.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new exception with null as its detail message.
     */
    public DukeException() {
    }

    /**
     * Constructs a new exception with specified message.
     *
     * @param message String representing the message.
     */
    public DukeException(String message) {
        super(message);
    }
}

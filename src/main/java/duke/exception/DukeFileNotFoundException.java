package main.java.duke.exception;

/**
 * Represents an exception in program operations when file is not found.
 */
public class DukeFileNotFoundException extends DukeException {

    /**
     * Constructs a new exception with null as its detail message.
     */
    public DukeFileNotFoundException() {
    }

    /**
     * Constructs a new exception with specified message.
     *
     * @param message String representing the message.
     */
    public DukeFileNotFoundException(String message) {
        super(message);
    }
}

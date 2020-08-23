package main.java.duke.exception;

public class DukeTaskNotFoundException extends DukeException {

    /**
     * Constructs a new exception with null as its detail message.
     */
    public DukeTaskNotFoundException() {
    }

    /**
     * Constructs a new exception with specified message.
     *
     * @param message String representing the message.
     */
    public DukeTaskNotFoundException(String message) {
        super(message);
    }
}

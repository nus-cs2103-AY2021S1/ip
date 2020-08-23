package main.java.duke.exception;

public class DukeInputNotRecognizedException extends DukeException {

    /**
     * Constructs a new exception with null as its detail message.
     */
    public DukeInputNotRecognizedException() {
    }

    /**
     * Constructs a new exception with specified message.
     *
     * @param message String representing the message.
     */
    public DukeInputNotRecognizedException(String message) {
        super(message);
    }
}

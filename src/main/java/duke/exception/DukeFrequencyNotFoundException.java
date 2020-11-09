package duke.exception;

public class DukeFrequencyNotFoundException extends DukeException {

    /**
     * Constructs a new exception with null as its detail message.
     */
    public DukeFrequencyNotFoundException() {
    }

    /**
     * Constructs a new exception with specified message.
     *
     * @param message String representing the message.
     */
    public DukeFrequencyNotFoundException(String message) {
        super(message);
    }
}

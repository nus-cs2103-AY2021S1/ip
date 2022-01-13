package duke;

/**
 * Represents exceptions thrown by Duke and other classes used by Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException object that displays a message.
     * @param message Message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the message of the DukeException.
     * @return String containing message of the DukeException.
     */
    @Override
    public String toString() {
        return getMessage();
    }

}

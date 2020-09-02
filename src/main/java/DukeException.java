
/**
 * The DukeException class extends the Exception class and
 * handles any input error e.g. InvalidCommand, IncompleteCommand
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException instance.
     *
     * @param message An error message informing and instructing
     * the user of the incorrect command and how they can try again
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Overrides the toString method to show the error message.
     *
     * @return The error message.
     */
    public String toString() {
        return getMessage();
    }
}


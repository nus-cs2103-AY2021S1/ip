
/**
 * The DukeInvalidTaskException class extends the DukeException
 * class and it handles the invalid task command format entered by the client.
 */
public class DukeInvalidTaskException extends DukeException {

    /**
     * Constructs a new DukeInvalidTaskException instance.
     *
     * @param message An error message informing and instructing
     * the user of the incorrect command and how they can try again
     */
    public DukeInvalidTaskException(String message) {
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

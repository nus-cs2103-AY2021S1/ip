
/**
 * The DukeIncompleteCommandException class extends the DukeException
 * class and it handles the incomplete commands entered by the client.
 */
public class DukeIncompleteCommandException extends DukeException {

    /**
     * Constructs a new DukeIncompleteCommandException instance.
     *
     * @param message An error message informing and instructing
     * the user of the incorrect command and how they can try again
     */
    public DukeIncompleteCommandException(String message) {
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

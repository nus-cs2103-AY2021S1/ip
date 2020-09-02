
/**
 * The DukeInvalidCommandException class extends the DukeException
 * class and it handles the invalid command format entered by the client.
 */
public class DukeInvalidCommandException extends DukeException {


    /**
     * Constructs a new DukeInvalidCommandException instance.
     *
     * @param message An error message informing and instructing
     * the user of the incorrect command and how they can try again
     */
    public DukeInvalidCommandException(String message) {
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


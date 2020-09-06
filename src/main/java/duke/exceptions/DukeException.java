package duke.exceptions;

/**
 * Represents an exception in Duke.
 */
public class DukeException extends Exception {
    /**
     * Initializes a Duke exception.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("%s Please try again!", super.getMessage());
    }
}

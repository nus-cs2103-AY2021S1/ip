package duke.common.exception;

/**
 * Represents an exception thrown in Duke.
 */
public class DukeException extends Exception {
    /**
     * Class constructor.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message.
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}

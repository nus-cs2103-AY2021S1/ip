package duke.exception;

/**
 * Represents an exception thrown in Duke.
 */
public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}

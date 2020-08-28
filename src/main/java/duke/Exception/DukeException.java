package duke.Exception;

/**
 * Deals with all exceptions related to the duke application.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

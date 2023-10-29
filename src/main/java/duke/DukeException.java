package duke;

/**
 * Handles exceptions related to Duke.
 */
public class DukeException extends Exception {
    private String exception;

    public DukeException(String error) {
        exception = error;
    }

    @Override
    public String getMessage() {
        return exception;
    }
}

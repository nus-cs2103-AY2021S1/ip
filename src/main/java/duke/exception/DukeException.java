package duke.exception;

/**
 * Represents an exception that is thrown when the task inputted by the user is not valid.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}


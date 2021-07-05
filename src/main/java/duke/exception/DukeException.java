package duke.exception;

/**
 * Encapsulates the exception system that Duke uses to handle
 * program issues that occur during its runtime.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }
}

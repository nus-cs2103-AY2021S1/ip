package duke;

/**
 * Exception class that encapsulates a Duke specific Exception.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

package duke.common;

/**
 * Signals custom errors and outputs a corresponding message.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }
}

package duke.data.exception;

/**
 * Signals that some given duke.data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    public IllegalValueException(String message) {
        super(message);
    }
}

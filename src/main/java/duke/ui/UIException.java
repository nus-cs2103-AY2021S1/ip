package duke.ui;

/**
 * Represents an exception that happened in the UI logic.
 */
public class UIException extends Exception {
    public UIException (String message) {
        super(message);
    }
}

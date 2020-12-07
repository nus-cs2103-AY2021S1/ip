package duke;

/**
 * Custom Exception for the project: duke.Duke
 */
public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    /**
     * Create a DukeException with custom message.
     * @param message Message to be displayed to user.
     */
    public DukeException(String message) {
        super(message);
        assert !message.equals("") : "Empty Duke Exception Message";
    }
}

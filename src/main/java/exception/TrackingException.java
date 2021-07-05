package exception;

/**
 * Exception when date/time is empty for events and deadline tasks.
 */

public class TrackingException extends DukeException {
    protected String command;
    public TrackingException(String command) {
        this.command = command;
    }

    public String toString() {
        return "Oops! Date/Time is required for " + this.command;
    }
}

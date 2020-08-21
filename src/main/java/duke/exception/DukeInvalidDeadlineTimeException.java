package duke.exception;

/**
 * Represents a Duke exception in which the deadline time input is invalid.
 */
public class DukeInvalidDeadlineTimeException extends DukeInvalidTaskTimeException {
    @Override
    public String toString() {
        return "ERROR: Usage: <deadline> <description> /by <time>\n"
                + "    Time formatting: dd-MM-yyyy HH:mm";
    }
}

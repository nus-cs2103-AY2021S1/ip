package duke.exception;

/**
 * Represents a Duke exception in which the task time input is invalid.
 */
public class DukeInvalidTaskTimeException extends DukeTaskException {
    @Override
    public String toString() {
        return "ERROR: Please specify a correct date/time for this task!\n"
                + "    Time formatting: dd-MM-yyyy HH:mm";
    }
}

package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the input format for the deadline command is invalid.
 */
public class DukeDeadlineFormatException extends DukeException {

    public static final String ERROR_DEADLINE_FORMAT = "The description of 'deadline' should be accompaw-nied"
            + " by '/by' followed by the date in this format: 'yyyy-MM-dd HH:mm'";
    /**
     * DukeDeadlineFormatException constructor.
     */
    public DukeDeadlineFormatException() {
        super(ERROR_DEADLINE_FORMAT);
    }
}

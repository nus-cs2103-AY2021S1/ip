package duke.exception;

public class DukeBadDeadlineDateException extends DukeException {
    private static final String BAD_DEADLINE_DATE_ERROR = "HELLO! Please fill up due date for the deadline. "
            + "Can't be empty!!! \uD83D\uDE20";

    /**
     * Initialises DukeBadDeadlineException object by calling constructor of DukeException object with appropriate
     * message
     */
    public DukeBadDeadlineDateException() {
        super(HORIZONTAL_RULE + "\n" + BAD_DEADLINE_DATE_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

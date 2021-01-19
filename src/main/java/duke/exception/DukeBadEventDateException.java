package duke.exception;

public class DukeBadEventDateException extends DukeException {
    private static final String BAD_EVENT_DATE_ERROR = "HELLO! Please fill up the date for the event. "
            + "Can't be empty!!! \uD83D\uDE20";

    /**
     * Initialises DukeBadEventDateException object by calling constructor of DukeException object with appropriate
     * message
     */
    public DukeBadEventDateException() {
        super(HORIZONTAL_RULE + "\n" + BAD_EVENT_DATE_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

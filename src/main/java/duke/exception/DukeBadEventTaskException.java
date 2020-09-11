package duke.exception;

public class DukeBadEventTaskException extends DukeException {
    private static final String BAD_EVENT_TASK_ERROR = "HELLO! Please fill up the event details. "
            + "Can't be empty!!! \uD83D\uDE20";

    /**
     * Initialises DukeBadEventTaskException object by calling constructor of DukeException object with appropriate
     * message
     */
    public DukeBadEventTaskException() {
        super(HORIZONTAL_RULE + "\n" + BAD_EVENT_TASK_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

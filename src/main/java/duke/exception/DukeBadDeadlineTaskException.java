package duke.exception;

public class DukeBadDeadlineTaskException extends DukeException {
    private static final String BAD_DEADLINE_TASK_ERROR = "HELLO! Please fill up the task for the deadline. "
            + "Can't be empty!!! \uD83D\uDE20";

    /**
     * Initialises DukeBadDeadlineTaskException object by calling constructor of DukeException object with appropriate
     * message
     */
    public DukeBadDeadlineTaskException() {
        super(HORIZONTAL_RULE + "\n" + BAD_DEADLINE_TASK_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

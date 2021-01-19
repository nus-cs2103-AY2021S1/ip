package duke.exception;

public class DukeBadCommandException extends DukeException {
    private static final String BAD_COMMAND_ERROR = "I have literally no idea what you're asking of me. \uD83D\uDE20";

    /**
     * Initialises DukeBadCommandException object by calling constructor of DukeException object with appropriate
     * message
     */
    public DukeBadCommandException() {
        super(HORIZONTAL_RULE + "\n" + BAD_COMMAND_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

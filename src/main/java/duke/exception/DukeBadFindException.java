package duke.exception;

public class DukeBadFindException extends DukeException {
    private static final String BAD_FIND_ERROR = "HELLO! How can I find something if you don't tell me what to find?";

    /**
     * Initialises DukeBadFindException object by calling constructor of DukeException object with appropriate message
     */
    public DukeBadFindException() {
        super(HORIZONTAL_RULE + "\n" + BAD_FIND_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

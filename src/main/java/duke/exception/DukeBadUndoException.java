package duke.exception;

public class DukeBadUndoException extends DukeException {
    private static final String BAD_UNDO_ERROR = "What do you want from me? There's not even anything for me to undo!";

    /**
     * Initialises DukeBadFindException object by calling constructor of DukeException object with appropriate message
     */
    public DukeBadUndoException() {
        super(HORIZONTAL_RULE + "\n" + BAD_UNDO_ERROR + "\n" + HORIZONTAL_RULE);
    }
}

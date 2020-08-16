package Exception;

public class InvalidActionException extends DukeException {
    private static final String DESCRIPTION = "The action is invalid.";
    public InvalidActionException() {
        super(DESCRIPTION);
    }
}

package exception;

public class EmptyActionException extends DukeException {
    private static final String DESCRIPTION = "The description of a command cannot be empty.";
    public EmptyActionException() {
        super(DESCRIPTION);
    }
}

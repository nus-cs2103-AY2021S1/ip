package exception;

public class InvalidCommandException extends DukeException {
    private static final String DESCRIPTION = "I'm sorry, but I don't know what that means :-(";
    public InvalidCommandException() {
        super(DESCRIPTION);
    }
}
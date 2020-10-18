package duke.exceptions;

public class NoAccessException extends DukeException {
    private static final String ERROR_MSG = "No Access for Command. Please login in first.";
    public NoAccessException() {
        super(ERROR_MSG);
    }
}

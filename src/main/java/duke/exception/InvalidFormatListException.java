package duke.exception;

public class InvalidFormatListException extends DukeException {
    private static final String LIST_EXCEPTION = " ☹ OOPS! Did you mean list? "
            + "(Note: There should not be anything behind list)";
    public InvalidFormatListException() {
        super(LIST_EXCEPTION);
    }
}

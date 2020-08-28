package duke.exception;

public class InvalidFormatByeException extends DukeException {
    private static final String BYE_EXCEPTION = " ☹ OOPS! Did you mean bye? "
            + "(Note: There should not be anything behind bye)";
    public InvalidFormatByeException() {
        super(BYE_EXCEPTION);
    }
}

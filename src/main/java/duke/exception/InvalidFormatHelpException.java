package duke.exception;

public class InvalidFormatHelpException extends DukeException {
    private static final String HELP_EXCEPTION = " ☹ OOPS! Did you mean help? "
            + "(Note: There should not be anything behind help)";
    public InvalidFormatHelpException() {
        super(HELP_EXCEPTION);
    }
}

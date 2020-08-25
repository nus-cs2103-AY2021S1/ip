package duke.exceptions;

public class DukeDateTimeParseException extends DukeException {
    final static String ERROR_INVALID_DATE = "⚠⚠⚠ Please input the proper date in this"
            + '\n' + "     " + " format: 'yyyy-mm-dd'";
    public DukeDateTimeParseException() {
        super(ERROR_INVALID_DATE);
    }
}

package duke.exceptions;

public class DukeDateTimeParseException extends DukeException {
    public final static String ERROR_INVALID_DATE = "⚠⚠⚠ Please input the proper date in this"
            + '\n' + "     " + " format: 'yyyy-MM-dd HH:mm'";
    public DukeDateTimeParseException() {
        super(ERROR_INVALID_DATE);
    }
}

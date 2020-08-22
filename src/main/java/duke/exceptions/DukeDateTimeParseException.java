package duke.exceptions;

public class DukeDateTimeParseException extends IllegalArgumentException {

    public DukeDateTimeParseException() {
        super("OOPS! Invalid date / time");
    }
}

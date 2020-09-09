package luke.exception;

public class LukeDateTimeException extends LukeException {
    public LukeDateTimeException(String string) {
        super("Unable to read date and time. Please enter the date and time in the format 'DD-MM-YYYY HHMM'");
    }
}

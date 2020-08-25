package duke.exception;

/** An exception when the date time given is invalid. */
public class InvalidDateTimeException extends InvalidDateException {

    /** The valid date time format that Duke accepts. */
    public static String DateTimeFormat = "The following DateTimeFormat is valid:\n"
            + "{YYYY-MM-DD}T{HH:MM:SS}\n"
            + "{YYYY-MM-DD}T{HH:MM}\n"
            + "{YYYY-MM-DD}\n"
            + "{HH:MM:SS}\n"
            + "{HH:MM}";

    /** Constructs an @InvalidDateTimeException. */
    public InvalidDateTimeException() {
        super("" + DateTimeFormat);
    }

    /**
     * Constructs an @InvalidDateTimeException with the given message.
     *
     * @param msg The given message.
     */
    protected InvalidDateTimeException(String msg) {
        super(msg + "\n\n" + DateTimeFormat);
    }
}
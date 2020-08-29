package duke.exception;

/** An exception that occurs when the given date time format for a deadline is invalid. */
public class EventInvalidDate extends InvalidDateTimeException {

    /** Constructs an EventInvalidDate exception. */
    public EventInvalidDate() {
        super("OOPS. You need to put \"/at [DateTimeFormat]\" or\n"
            + "\"/at [DateTimeFormat] to [DateTimeFormat]\" after an duke.task.Event.");
    }
}

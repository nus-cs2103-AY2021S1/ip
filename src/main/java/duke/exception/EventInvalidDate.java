package duke.exception;

public class EventInvalidDate extends InvalidDateTimeException {
    public EventInvalidDate() {
        super("OOPS. You need to put \"/at [DateTimeFormat]\" or\n" +
                "\"/at [DateTimeFormat] to [DateTimeFormat]\" after an duke.task.Event.");
    }
}

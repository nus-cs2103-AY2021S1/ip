public class EventInvalidDate extends InvalidDateTimeException {
    protected EventInvalidDate() {
        super("OOPS. You need to put \"/at [DateTimeFormat]\" or\n" +
                "\"/at [DateTimeFormat] to [DateTimeFormat]\" after an Event.");
    }
}

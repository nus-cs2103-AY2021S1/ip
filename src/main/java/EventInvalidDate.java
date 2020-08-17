public class EventInvalidDate extends InvalidDateException {
    protected EventInvalidDate() {
        super("OOPS. You need to put \"/at [date]\" after inputting an Event.");
    }
}

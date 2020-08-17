public class Event extends Task {

    private static final String MESSAGE_MISSING_DATETIME = "Did you casually forget to put in the event date/time?";
    private final String dateTime;

    public Event(String name, String dateTime) throws BlankTaskException, MissingDateTimeException {
        super(name);
        if (dateTime.isBlank()) {
            throw new MissingDateTimeException(MESSAGE_MISSING_DATETIME);
        }
        this.dateTime = dateTime.strip();
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}

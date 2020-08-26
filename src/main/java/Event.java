public class Event extends Task {

    private final DateAndTime datetime;

    public Event(String description, String date, String time) throws InvalidDateTimeException {
        super(description);
        this.datetime = new DateAndTime(date, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.datetime + ")";
    }
}

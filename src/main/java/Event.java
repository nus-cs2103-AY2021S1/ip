public class Event extends TimedTask {

    public Event(String description, String datetime) {
        super(description, datetime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.datetimeString() + ")";
    }
}

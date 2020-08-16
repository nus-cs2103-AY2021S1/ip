public class Event extends Task {

    private final String dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
